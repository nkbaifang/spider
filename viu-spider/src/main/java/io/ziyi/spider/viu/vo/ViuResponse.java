package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuResponse<T> extends CommonVO {

    @JsonProperty("status")
    private ViuStatus status;

    @JsonProperty("server")
    private ViuServer server;

    @JsonProperty("data")
    private T data;

    public ViuStatus getStatus() {
        return status;
    }

    public void setStatus(ViuStatus status) {
        this.status = status;
    }

    public ViuServer getServer() {
        return server;
    }

    public void setServer(ViuServer server) {
        this.server = server;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @JsonIgnore
    public boolean ok() {
        return status != null && status.getCode() == 0;
    }
}
