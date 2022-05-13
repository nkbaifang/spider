package io.ziyi.spider.viu.web;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import comm.base.tools.api.model.ApiResponse;
import io.ziyi.spider.util.JsonUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown=true)
public class CommonResponse<T> extends ApiResponse<T> {

    public static class Builder<T> {

        private final transient long startTime;
        private int code;
        private String message;
        private T result;

        public Builder() {
            this.startTime = System.currentTimeMillis();
        }

        public int code() {
            return code;
        }

        public Builder<T> code(int code) {
            this.code = code;
            return this;
        }

        public String message() {
            return message;
        }

        public Builder<T> message(String message) {
            this.message = message;
            return this;
        }

        public T result() {
            return result;
        }

        public Builder<T> result(T result) {
            this.result = result;
            return this;
        }

        protected final long duration() {
            return System.currentTimeMillis() - startTime;
        }

        public CommonResponse<T> build() {
            CommonResponse<T> response = new CommonResponse<>(code(), message(), result());
            response.setQueryDuration(duration());
            return response;
        }

        public CommonResponse<T> ok(T result) {
            return code(0).message(null).result(result).build();
        }

        public CommonResponse<T> error(int errorCode, String message) {
            return code(errorCode).message(message).result(null).build();
        }

        public CommonResponse<T> error(Throwable error) {
            return error(500, error.getMessage());
        }
    }

    public CommonResponse() {
    }

    public CommonResponse(int errorCode, String message, T result) {
        super.setErrCode(errorCode);
        super.setMessage(message);
        super.setResult(result);
    }

    @JsonIgnore
    public boolean isSuccessful() {
        return getErrCode() == 0;
    }

    public static <R> Builder<R> builder() {
        return new Builder<>();
    }

    public static <R> CommonResponse<R> ok(R result) {
        return new CommonResponse<>(0, null, result);
    }

    public static <R> CommonResponse<R> error(int errorCode, String message) {
        return new CommonResponse<>(errorCode, message, null);
    }

    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch ( Exception ex ) {
            return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
        }
    }
}
