package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuServer extends CommonVO {

    @JsonProperty("time")
    private long time;

    @JsonProperty("area")
    private ViuArea area;

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public ViuArea getArea() {
        return area;
    }

    public void setArea(ViuArea area) {
        this.area = area;
    }
}
