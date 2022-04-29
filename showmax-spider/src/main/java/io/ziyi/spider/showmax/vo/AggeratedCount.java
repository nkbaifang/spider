package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AggeratedCount extends Count {

    private static final long serialVersionUID = 0xcf5272add6013c29L;

    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
