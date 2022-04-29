package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class Thumbnail extends CommonVO {

    private static final long serialVersionUID = 0xbd68e7e1cf05d8a5L;

    @JsonProperty("link")
    private String link;

    @JsonProperty("type")
    private String type;

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
