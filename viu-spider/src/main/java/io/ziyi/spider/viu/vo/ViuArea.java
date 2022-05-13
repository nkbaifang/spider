package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuArea extends CommonVO {

    @JsonProperty("area_id")
    private int areaId;

    @JsonProperty("language")
    private List<ViuLanguage> languages;

    public int getAreaId() {
        return areaId;
    }

    public void setAreaId(int areaId) {
        this.areaId = areaId;
    }

    public List<ViuLanguage> getLanguages() {
        return languages;
    }

    public void setLanguages(List<ViuLanguage> languages) {
        this.languages = languages;
    }
}
