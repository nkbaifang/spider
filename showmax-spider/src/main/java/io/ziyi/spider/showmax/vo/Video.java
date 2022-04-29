package io.ziyi.spider.showmax.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.math.BigDecimal;
import java.util.List;

public class Video extends CommonVO {

    private static final long serialVersionUID = 0xf60787e92aee54ecL;

    @JsonProperty("id")
    private String id;

    @JsonProperty("language")
    private Language language;

    @JsonProperty("usage")
    private String usage;

    @JsonProperty("ending_time")
    private BigDecimal endingTime;

    @JsonProperty("duration")
    private BigDecimal duration;

    @JsonProperty("height")
    private Integer height;

    @JsonProperty("width")
    private Integer width;

    @JsonProperty("link")
    private String link;

    @JsonProperty("subtitles")
    private List<Subtitle> subtitles;

    @JsonProperty("thumbnails")
    private List<Thumbnail> thumbnails;

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

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public BigDecimal getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(BigDecimal endingTime) {
        this.endingTime = endingTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<Subtitle> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<Subtitle> subtitles) {
        this.subtitles = subtitles;
    }

    public List<Thumbnail> getThumbnails() {
        return thumbnails;
    }

    public void setThumbnails(List<Thumbnail> thumbnails) {
        this.thumbnails = thumbnails;
    }
}
