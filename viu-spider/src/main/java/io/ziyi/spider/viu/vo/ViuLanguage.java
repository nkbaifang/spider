package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuLanguage extends CommonVO {

    @JsonProperty("label")
    private String label;

    @JsonProperty("mark")
    private String mark;

    @JsonProperty("is_default")
    private int isDefault;

    @JsonProperty("language_flag_id")
    private int languageFlagId;

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public int getLanguageFlagId() {
        return languageFlagId;
    }

    public void setLanguageFlagId(int languageFlagId) {
        this.languageFlagId = languageFlagId;
    }
}
