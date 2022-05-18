package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.viu.common.BaseComponent;
import io.ziyi.spider.viu.dao.SeriesDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.BooleanSupplier;

public class BaseService extends BaseComponent {

    private static class MediaServiceThreadFactory implements ThreadFactory, Thread.UncaughtExceptionHandler {

        private final CommonLogger logger;
        private final ThreadGroup group;
        private final AtomicLong threadCount;

        private MediaServiceThreadFactory(String groupName) {
            this.logger = CommonLogger.getLogger(groupName + "-logger");
            this.group = new ThreadGroup(groupName + "-scheduler");
            this.threadCount = new AtomicLong(0L);
        }

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

    @Autowired
    protected SeriesDao dao;

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final Duration redisListPopTimeout;

    private final ScheduledExecutorService executor;

    protected BaseService(String name) {
        long listPopTimeout = ConfigTools3.getLong("spider.viu.redis.list.pop.timeout-millis", 100L);
        this.redisListPopTimeout = Duration.ofMillis(listPopTimeout);

        int corePoolSize = ConfigTools3.getInt("spider.viu.executor.core-pool-size", 4);
        int maximumPoolSize = ConfigTools3.getInt("spider.viu.executor.maximum-pool-size", 40);
        long keepAliveTime = ConfigTools3.getLong("spider.viu.executor.keep-alive-seconds", 5L);
        int maximumQueueSize = ConfigTools3.getInt("spider.viu.executor.maximum-queue-size", 10000);

        BlockingQueue<Runnable> queue = maximumQueueSize > 0 ? new LinkedBlockingQueue<>(maximumQueueSize) : new LinkedBlockingQueue<>();

        executor = new ScheduledThreadPoolExecutor(corePoolSize, new MediaServiceThreadFactory(name));
        //executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.SECONDS, queue, new MediaServiceThreadFactory(name));
    }

    protected void executeAsyncAction(Runnable command) {
        executor.execute(command);
    }

    protected void schedule(Runnable command, long initDelay, long fixedDelay) {
        executor.scheduleWithFixedDelay(command, initDelay, fixedDelay, TimeUnit.MILLISECONDS);
    }

    protected boolean lockedAction(String key, BooleanSupplier action) {
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

    protected void releaseActionLock(String key) {
        redisTemplate.delete(key);
    }

    protected boolean isActionLocked(String key) {
        String time = redisTemplate.boundValueOps(key).get();
        return time != null;
    }

    protected void appendTaskValue(String key, String value) {
        redisTemplate.boundListOps(key).rightPush(value);
    }

    protected long appendTaskValue(String key, Collection<String> values) {
        BoundListOperations<String, String> listOps = redisTemplate.boundListOps(key);
        return values.stream().mapToLong(value -> {
            Long k = listOps.rightPush(value);
            return k == null ? 0L : k;
        }).sum();
    }

    protected String peekTaskValue(String key) {
        return redisTemplate.boundListOps(key).leftPop(redisListPopTimeout);
    }
}
