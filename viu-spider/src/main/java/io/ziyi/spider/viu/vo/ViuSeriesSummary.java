package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuSeriesSummary extends CommonVO {

    @JsonProperty("series_id")
    private long seriesId;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("cp_name")
    private String cpName;

    @JsonProperty("is_movie")
    private int isMovie;

    @JsonProperty("product_total")
    private int productTotal;

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getIsMovie() {
        return isMovie;
    }

    public void setIsMovie(int isMovie) {
        this.isMovie = isMovie;
    }

    public int getProductTotal() {
        return productTotal;
    }

    public void setProductTotal(int productTotal) {
        this.productTotal = productTotal;
    }
}
