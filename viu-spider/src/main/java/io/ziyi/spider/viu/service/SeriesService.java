package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.dao.SeriesDao;
import io.ziyi.spider.viu.model.Category;
import io.ziyi.spider.viu.model.Series;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

@EnableScheduling
@Component
public class SeriesService extends BaseComponent {

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
    private SeriesDao dao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final ThreadPoolExecutor executor;

    public SeriesService() {
        int corePoolSize = ConfigTools3.getInt("spider.viu.executor.core-pool-size", 4);
        int maximumPoolSize = ConfigTools3.getInt("spider.viu.executor.maximum-pool-size", 40);
        long keepAliveTime = ConfigTools3.getLong("spider.viu.executor.keep-alive-seconds", 5L);
        int maximumQueueSize = ConfigTools3.getInt("spider.viu.executor.maximum-queue-size", 10000);

        BlockingQueue<Runnable> queue = maximumQueueSize > 0 ? new LinkedBlockingQueue<>(maximumQueueSize) : new LinkedBlockingQueue<>();

        executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, threadFactory);
    }

    @PostConstruct
    protected void startup() {
        //scheduler.schedule(this::detail, 2L, TimeUnit.SECONDS);
        //scheduler.schedule(this::download, 2L, TimeUnit.SECONDS);
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

    private void refreshSeries(ViuSpider spider, long categoryId) {
        logger.info("Refresh series", "Start refreshing series. category={}", categoryId);
        try {
            spider.searchSeries(categoryId, items -> {
                logger.info("Search series", "Found series: count={}", items.size());
                items.forEach(item -> {
                    Series series = new Series(item);
                    dao.saveSeries(series, categoryId);
                });
            });
        } catch ( Throwable error ) {
            logger.error(error, "Refresh series failed", "");
        }
        logger.info("Refresh series", "Finished refreshing series.");
    }

    public void refreshSeries(long categoryId) {
        boolean acquired = lockedAction("spider.viu.movie.refresh", () -> {
            logger.info("Refresh series", "Start refreshing series.");
            executor.execute(() -> {
                ViuSpider spider = new ViuSpider(true);
                refreshSeries(spider, categoryId);
                releaseActionLock("spider.viu.movie.refresh");
            });
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh series", "Process already started.");
        }
    }

    public void refreshSeries() {
        boolean acquired = lockedAction("spider.viu.movie.refresh", () -> {
            logger.info("Refresh series", "Start refreshing series.");

            List<Category> categories = dao.findCategories();

            executor.execute(() -> {
                ViuSpider spider = new ViuSpider(false);
                categories.forEach(category -> {
                    refreshSeries(spider, category.getId());
                });
                releaseActionLock("spider.viu.movie.refresh");
            });
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh movie", "Process already started.");
        }
    }
}
