package io.ziyi.spider.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.slf4j.helpers.MessageFormatter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class CommonLogger {

    private final Logger logger;

    private CommonLogger(Logger logger) {
        this.logger = logger;
    }

    public static CommonLogger getLogger(Class<?> loggerClass) {
        return new CommonLogger(LoggerFactory.getLogger(loggerClass));
    }

    public static CommonLogger getLogger(String name) {
        return new CommonLogger(LoggerFactory.getLogger(name));
    }

    public Logger getLogger() {
        return logger;
    }

    private String formatMessage(String pattern, Object... params) {
        return pattern == null ? "" : MessageFormatter.arrayFormat(pattern, params).getMessage();
    }

    private void logMessage(Consumer<String> consumer, String summary, String pattern, Object... params) {
        MDC.put("logSummary", summary);
        String message = formatMessage(pattern, params);
        consumer.accept(message);
        MDC.remove("logSummary");
    }

    private void logMessage(BiConsumer<String, Throwable> consumer, Throwable error, String summary, String pattern, Object... params) {
        MDC.put("logSummary", summary);
        String message = formatMessage(pattern, params);
        consumer.accept(message, error);
        MDC.remove("logSummary");
    }

    public void debug(String summary, String pattern, Object... params) {
        logMessage(logger::debug, summary, pattern, params);
    }

    public void debug(Throwable error, String summary, String pattern, Object... params) {
        logMessage(logger::debug, error, summary, pattern, params);
    }

    public void info(String summary, String pattern, Object... params) {
        logMessage(logger::info, summary, pattern, params);
    }

    public void warn(String summary, String pattern, Object... params) {
        logMessage(logger::warn, summary, pattern, params);
    }

    public void warn(Throwable error, String summary, String pattern, Object... params) {
        logMessage(logger::warn, error, summary, pattern, params);
    }

    public void error(String summary, String pattern, Object... params) {
        logMessage(logger::error, summary, pattern, params);
    }

    public void error(Throwable error, String summary, String pattern, Object... params) {
        logMessage(logger::error, error, summary, pattern, params);
    }

    private String getContext(String key) {
        String value = MDC.get(key);
        if ( value == null ) {
            value = "";
        }
        return value;
    }
}
