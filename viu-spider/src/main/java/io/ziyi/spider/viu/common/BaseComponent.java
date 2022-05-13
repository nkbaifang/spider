package io.ziyi.spider.viu.common;

import io.ziyi.spider.util.CommonLogger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Objects;

public abstract class BaseComponent implements ApplicationContextAware, InitializingBean {

    protected final CommonLogger logger = CommonLogger.getLogger(this.getClass());

    private ApplicationContext ctx;

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        logger.info("Application", "ApplicationContext initialized: bean={}", getClass());
        this.ctx = ctx;
    }

    protected <T> T findBean(Class<T> type) {
        Objects.requireNonNull(ctx, "ApplicationContext not initialized.");
        return ctx.getBean(type);
    }

    protected <T> T findBean(Class<T> type, String name) {
        Objects.requireNonNull(ctx, "ApplicationContext not initialized.");
        return ctx.getBean(name, type);
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }
}