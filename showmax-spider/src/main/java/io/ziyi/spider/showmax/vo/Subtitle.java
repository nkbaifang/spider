package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class Subtitle extends CommonVO {

    private static final long serialVersionUID = 0x4c5145e903cee4e3L;

    @JsonProperty("id")
    private String id;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("link")
    private String link;

    @JsonProperty("type")
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
