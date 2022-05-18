package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuSeries extends CommonVO {

    @JsonProperty("series_id")
    private long seriesId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("release_time")
    private long releaseTime;

    @JsonProperty("cp_name")
    private String cpName;

    @JsonProperty("allow_tv")
    private int allowTv;

    @JsonProperty("series_language")
    private String seriesLanguage;

    @JsonProperty("product")
    private List<ViuProductSummary> products;

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public long getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(long releaseTime) {
        this.releaseTime = releaseTime;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getAllowTv() {
        return allowTv;
    }

    public void setAllowTv(int allowTv) {
        this.allowTv = allowTv;
    }

    public String getSeriesLanguage() {
        return seriesLanguage;
    }

    public void setSeriesLanguage(String seriesLanguage) {
        this.seriesLanguage = seriesLanguage;
    }

    public List<ViuProductSummary> getProducts() {
        return products;
    }

    public void setProducts(List<ViuProductSummary> products) {
        this.products = products;
    }
}
