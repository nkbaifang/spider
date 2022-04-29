package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Language extends Count {

    private static final long serialVersionUID = 0xb478ce3e09c08029L;

    @JsonProperty("iso_639_3")
    private String code;

    @JsonProperty("name")
    private String name;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
