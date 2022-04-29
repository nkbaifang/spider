package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class Rating extends CommonVO {

    private static final long serialVersionUID = 0xcd09980165e383b0L;

    @JsonProperty("level")
    private String level;

    @JsonProperty("name")
    private String name;

    @JsonProperty("slug")
    private String slug;

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }
}
