package io.ziyi.spider.http;

import java.io.IOException;

public class HttpException extends Exception {

    private final int status;
    private final String message;
    private String text;

    public HttpException(IOException cause) {
        super(cause);
        this.status = 0;
        this.message = cause.getMessage();
    }

    public HttpException(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return String.format("HTTP %d: %s", status, message);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
