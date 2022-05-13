package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuSeries extends CommonVO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("is_movie")
    private int isMovie;

    @JsonProperty("series_id")
    private long seriesId;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("cp_name")
    private String cpName;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("released_product_total")
    private int releasedProductTotal;

    @JsonProperty("allow_tv")
    private int allowTv;

    @JsonProperty("free_time")
    private long freeTime;

    @JsonProperty("offline_time")
    private long offlineTime;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIsMovie() {
        return isMovie;
    }

    public void setIsMovie(int isMovie) {
        this.isMovie = isMovie;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getReleasedProductTotal() {
        return releasedProductTotal;
    }

    public void setReleasedProductTotal(int releasedProductTotal) {
        this.releasedProductTotal = releasedProductTotal;
    }

    public int getAllowTv() {
        return allowTv;
    }

    public void setAllowTv(int allowTv) {
        this.allowTv = allowTv;
    }

    public long getFreeTime() {
        return freeTime;
    }

    public void setFreeTime(long freeTime) {
        this.freeTime = freeTime;
    }

    public long getOfflineTime() {
        return offlineTime;
    }

    public void setOfflineTime(long offlineTime) {
        this.offlineTime = offlineTime;
    }
}
