package io.ziyi.spider.showmax.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.showmax.common.BaseComponent;
import io.ziyi.spider.showmax.dao.MediaDao;
import io.ziyi.spider.showmax.model.Movie;
import io.ziyi.spider.showmax.model.MovieMpd;
import io.ziyi.spider.showmax.model.MovieVideo;
import io.ziyi.spider.showmax.utils.FileUtils;
import io.ziyi.spider.showmax.vo.AggeratedCount;
import io.ziyi.spider.showmax.vo.Asset;
import io.ziyi.spider.showmax.vo.Item;
import io.ziyi.spider.showmax.vo.Video;
import io.ziyi.spider.util.CommonLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.io.File;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

@EnableScheduling
@Component
public class MediaService extends BaseComponent {

    private static class MediaServiceThreadFactory implements ThreadFactory, Thread.UncaughtExceptionHandler {

        private final CommonLogger logger = CommonLogger.getLogger(getClass());
        private final ThreadGroup group = new ThreadGroup("media-scheduler");
        private final AtomicLong threadCount = new AtomicLong(0L);

        @Override
        public Thread newThread(Runnable task) {
            Thread thread = new Thread(group, task, group.getName() + "-" + threadCount.getAndIncrement());
            thread.setUncaughtExceptionHandler(this);
            return thread;
        }

        @Override
        public void uncaughtException(Thread thread, Throwable error) {
            logger.error(error, "Uncaught exception", "Detail: thread={}, message={}", thread.getName(), error.getMessage());
        }
    }

    private static final MediaServiceThreadFactory threadFactory = new MediaServiceThreadFactory();

    @Autowired
    private MediaDao dao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final ScheduledExecutorService scheduler;

    public MediaService() {
        int corePoolSize = ConfigTools3.getInt("spider.showmax.scheduler.core-pool-size", 4);
        scheduler = new ScheduledThreadPoolExecutor(corePoolSize, threadFactory);
    }

    @PostConstruct
    protected void startup() {
        scheduler.schedule(this::detail, 2L, TimeUnit.SECONDS);
        scheduler.schedule(this::download, 2L, TimeUnit.SECONDS);
    }

    private void appendMovieToDownload(String id) {
        redisTemplate.boundListOps("spider.showmax.movies.download.queue").rightPush(id);
    }

    private String peekMovieToDownload(long millis) {
        return redisTemplate.boundListOps("spider.showmax.movies.download.queue").leftPop(Duration.ofMillis(millis));
    }

    private void appendMovieToSearch(String id, int retryCount) {
        redisTemplate.boundListOps("spider.showmax.movies.detail.queue").rightPush(id);
        redisTemplate.boundValueOps("spider.showmax.movie.detail.retry." + id).set(String.valueOf(retryCount));
    }

    private AggeratedCount peekMovieToSearch(long millis) {
        String id = redisTemplate.boundListOps("spider.showmax.movies.detail.queue").leftPop(Duration.ofMillis(millis));
        if ( id == null ) {
            return null;
        }

        AggeratedCount result = new AggeratedCount();
        result.setId(id);
        String value = redisTemplate.boundValueOps("spider.showmax.movie.detail.retry." + id).get();
        if ( value == null ) {
            result.setCount(0);
        } else {
            try {
                result.setCount(Integer.valueOf(value));
            } catch ( NumberFormatException ex ) {
                result.setCount(0);
            }
        }
        return result;
    }

    private void detail() {
        long millis = ConfigTools3.getLong("spider.showmax.redis.list-pop-timeout-millis", 200L);
        AggeratedCount c = peekMovieToSearch(millis);
        if ( c == null ) {
            logger.info("Find movie detail", "Empty queue");
            return;
        }

        ShowmaxSpider spider = new ShowmaxSpider(false);
        MediaService service = findBean(MediaService.class);
        int count = 0;
        do {
            String id = c.getId();
            int retryCount = c.getCount();
            boolean retry = true;
            Item item = spider.queryMovieDetail(id);
            if ( item != null ) {
                try {
                    service.saveMovie(item);
                    count++;
                    retry = false;
                } catch ( Throwable error ) {
                    logger.error(error, "Failed to save movie", "Detail: id={}", id);
                }
            } else {
                logger.warn("Cannot find movie detail", "Movie: id={}", id);
            }

            if ( retry && retryCount < 3 ) {
                logger.info("Find movie detail", "Retry: id={}, retryCount={}", id, retryCount);
                appendMovieToSearch(id, retryCount + 1);
            } else {
                redisTemplate.delete("spider.showmax.movie.detail.retry." + id);
            }

            c = peekMovieToSearch(millis);
        } while ( c != null );
        logger.info("Find movie detail", "Result: count={}", count);
    }

    @Transactional(rollbackFor = Exception.class)
    protected int saveMovie(Item item) {
        logger.info("Save movie", "Item: id={}, title={}", item.getId(), item.getTitle());
        //Movie movie = new Movie(item);

        Movie movie = dao.findMovie(item.getId());
        if ( movie == null ) {
            movie = new Movie(item);
            dao.saveMovie(movie);
            logger.info("Save movie", "Save new movie: id={}", movie.getId());
        } else {
            return 0;
        }

        int result = 0;
        List<Video> vs = item.getVideos();
        if ( vs != null ) {
            for ( Video v : vs ) {
                MovieVideo video = dao.findMovieVideo(v.getId());
                if ( video == null ) {
                    logger.info("Save movie", "Save new video: movie={}, id={}", movie.getId(), v.getId());
                    video = new MovieVideo(v);
                    video.setMovieId(movie.getId());
                    dao.saveVideo(video);
                    result++;
                }
            }
        }
        logger.info("Save movie", "After save: id={}, videos={}", movie.getId(), result);
        return result;
    }

    private void checkMovieDetailTasks() {
        String time = redisTemplate.boundValueOps("spider.showmax.movie.refresh").get();
        if ( time != null ) {
            detail();
            scheduler.schedule(this::checkMovieDetailTasks, 5L, TimeUnit.SECONDS);
        }
    }

    private boolean lockedAction(String key, BooleanSupplier action) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(action);

        BoundValueOperations<String, String> ops = redisTemplate.boundValueOps(key);
        Boolean acquired = ops.setIfAbsent(String.valueOf(System.currentTimeMillis()));
        if ( Boolean.TRUE.equals(acquired) ) {
            boolean release = action.getAsBoolean();
            if ( release ) {
                ops.getAndDelete();
            }
        }
        return acquired != null && acquired;
    }

    private void releaseActionLock(String key) {
        redisTemplate.delete(key);
    }

    private boolean isActionLocked(String key) {
        String time = redisTemplate.boundValueOps(key).get();
        return time != null;
    }

    public void refreshMovie() {
        boolean acquired = lockedAction("spider.showmax.movie.refresh", () -> {
            logger.info("Refresh movie", "Start refreshing movies.");
            scheduler.execute(() -> {
                ShowmaxSpider spider = new ShowmaxSpider(false);
                try {
                    spider.searchMovies(items -> {
                        logger.info("Search movie", "Found movies: count={}", items.size());
                        items.forEach(item -> appendMovieToSearch(item.getId(), 0));
                    });
                } catch ( Throwable error ) {
                    logger.error(error, "Search movie failed", "");
                }
                logger.info("Refresh movie", "Finished refreshing movies.");
                releaseActionLock("spider.showmax.movie.refresh");
            });
            scheduler.schedule(this::checkMovieDetailTasks, 5L, TimeUnit.SECONDS);
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh movie", "Process already started.");
        }
    }

    public void list(int start, int num) {
        ShowmaxSpider spider = new ShowmaxSpider(true);
        try {
            logger.info("Query asset", "Before: start={}, num={}", start, num);
            Asset asset = spider.queryAssets("eng", "movie", start, num);
            if ( asset != null ) {
                logger.info("Query asset", "After: start={}, num={}, count={}, total={}, remaining={}", start, num, asset.getCount(), asset.getTotal(), asset.getRemaining());
            } else {
                logger.info("Query asset", "After: start={}, num={}, asset=null", start, num);
            }
        } catch ( Exception ex ) {
            logger.warn(ex, "Query asset", "Failed to query asset. start={}, num={}", start, num);
        }
    }

    public int downloadMovie(String id) {
        Movie movie = dao.findMovie(id);
        if ( movie == null ) {
            logger.info("Download movie", "Movie not found: id={}", id);
            return 0;
        }

        ShowmaxSpider spider = new ShowmaxSpider(false);
        List<MovieMpd> list = downloadVideos(spider, movie.getId());
        for ( MovieMpd mpd : list ) {
            dao.saveMovieMpd(mpd);
        }
        return list.size();
    }

    private List<MovieMpd> downloadVideos(ShowmaxSpider spider, String movieId) {
        List<MovieVideo> videos = dao.findMovieVideos(movieId);
        if ( videos.isEmpty() ) {
            logger.info("Download movie", "Main videos not found: movie={}", movieId);
            return Collections.emptyList();
        }

        List<MovieMpd> result = new LinkedList<>();
        for ( MovieVideo video : videos ) {
            File target = FileUtils.createLocalFile(String.format("movie/%s/%s/.mpd", movieId, video.getId()));
            try {
                boolean ok = spider.downloadVideoMPD(video.getId(), target);
                logger.info("Download video", "Download video MPD. movie={}, video={}, ok={}", movieId, video.getId(), ok);
                if ( ok ) {
                    FileUtils.FileInfo info = FileUtils.readFileInfo(target);
                    MovieMpd mpd = new MovieMpd();
                    mpd.setId(video.getId());
                    mpd.setMovieId(movieId);
                    mpd.setDownloadTime(new Date());
                    mpd.setLength(info.getLength());
                    mpd.setFileSha1(info.getHash());
                    result.add(mpd);
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Download video", "Failed to download video MPD. movie={}, video={}", movieId, video.getId());
            }
        }
        return result;
    }

    private void download() {
        long millis = ConfigTools3.getLong("spider.showmax.redis.list-pop-timeout-millis", 200L);
        ShowmaxSpider spider = new ShowmaxSpider(false);
        int count = 0;
        String id = peekMovieToDownload(millis);

        while ( id != null ) {
            List<MovieMpd> list = downloadVideos(spider, id);
            list.forEach(dao::saveMovieMpd);
            count += list.size();
            id = peekMovieToDownload(millis);
        }
        logger.info("Download movie", "Result: count={}", count);
    }

    private void checkMovieDownloadTasks() {
        if ( isActionLocked("spider.showmax.movie.download") ) {
            download();
            releaseActionLock("spider.showmax.movie.download");
        }
    }

    public void downloadAll() {
        boolean acquired = lockedAction("spider.showmax.movie.download", () -> {
            logger.info("Download movie", "Start downloading movies.");
            List<Movie> movies = dao.findAllMovies();
            movies.forEach(movie -> appendMovieToDownload(movie.getId()));
            scheduler.schedule(this::checkMovieDownloadTasks, 2L, TimeUnit.SECONDS);
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh movie", "Process already started.");
        }
    }
}
