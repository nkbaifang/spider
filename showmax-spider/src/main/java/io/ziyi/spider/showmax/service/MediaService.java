package io.ziyi.spider.showmax.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.showmax.common.BaseComponent;
import io.ziyi.spider.showmax.dao.MediaDao;
import io.ziyi.spider.showmax.model.Movie;
import io.ziyi.spider.showmax.model.MovieVideo;
import io.ziyi.spider.showmax.utils.FileUtils;
import io.ziyi.spider.showmax.vo.AggeratedCount;
import io.ziyi.spider.showmax.vo.Item;
import io.ziyi.spider.showmax.vo.Video;
import io.ziyi.spider.util.CommonLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

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

        ShowmaxSpider spider = new ShowmaxSpider();
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
        Movie movie = new Movie(item);
        dao.saveMovie(movie);
        AtomicInteger vc = new AtomicInteger(0);
        List<Video> vs = item.getVideos();
        if ( vs != null ) {
            vs.forEach(v -> {
                MovieVideo video = new MovieVideo(v);
                video.setMovieId(movie.getId());
                dao.saveVideo(video);
                vc.getAndIncrement();
            });
        }
        int result = vc.get();
        logger.info("Save movie", "After save: id={}, videos={}", movie.getId(), result);
        return result;
    }

    private void checkMovieDetailTasks() {
        String time = redisTemplate.boundValueOps("spider.showmax.movie.refresh").get();
        if ( time != null ) {
            detail();
            scheduler.schedule(this::checkMovieDetailTasks, 5000L, TimeUnit.SECONDS);
        }
    }

    public void refreshMovie() {
        Boolean acquired = redisTemplate.boundValueOps("spider.showmax.movie.refresh").setIfAbsent(String.valueOf(System.currentTimeMillis()));
        if ( Boolean.TRUE.equals(acquired) ) {
            logger.info("Refresh movie", "Start refreshing movies.");
            scheduler.execute(() -> {
                ShowmaxSpider spider = new ShowmaxSpider();
                try {
                    spider.searchMovies(items -> {
                        logger.info("Search movie", "Found movies: count={}", items.size());
                        items.forEach(item -> appendMovieToSearch(item.getId(), 0));
                    });
                } catch ( Throwable error ) {
                    logger.error(error, "Search movie failed", "");
                }
                logger.info("Refresh movie", "Finished refreshing movies.");
                redisTemplate.delete("spider.showmax.movie.refresh");
            });
            scheduler.schedule(this::checkMovieDetailTasks, 5000L, TimeUnit.SECONDS);
        } else {
            logger.warn("Refresh movie", "Process already started.");
        }
    }

    public int downloadMovie(String id) {
        Movie movie = dao.findMovie(id);
        if ( movie == null ) {
            logger.info("Download movie", "Movie not found: id={}", id);
            return 0;
        }

        List<MovieVideo> videos = dao.findMovieVideos(movie.getId());
        if ( videos.isEmpty() ) {
            logger.info("Download movie", "Main videos not found: movie={}", movie.getId());
            return 0;
        }

        int count = 0;
        ShowmaxSpider spider = new ShowmaxSpider();
        for ( MovieVideo video : videos ) {
            File target = FileUtils.createLocalFile(String.format("movie/%s/%s/.mpd", movie.getId(), video.getId()));
            try {
                boolean ok = spider.downloadVideoMPD(video.getId(), target);
                logger.info("Download video", "Download video MPD. movie={}, video={}, ok={}", movie.getId(), video.getId(), ok);
                if ( ok ) {
                    count++;
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Download video", "Failed to download video MPD. movie={}, video={}", movie.getId(), video.getId());
            }
        }
        return count;
    }
}
