package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public abstract class Count extends CommonVO {

    @JsonProperty("count")
    private Integer count;

    @JsonIgnore
    public final boolean countAvailable() {
        return count != null;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
