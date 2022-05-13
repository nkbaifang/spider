package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuCategorySeriesTotal extends CommonVO {

    @JsonProperty("category_id")
    private long categoryId;

    @JsonProperty("series_total")
    private int seriesTotal;

    @JsonProperty("tagIds")
    private List<String> tagIds;

    @JsonProperty("tag_id")
    private int tagId;

    @JsonProperty("category_name")
    private String categoryName;

    @JsonProperty("update_cycle_description")
    private String updateCycleDescription;

    @JsonProperty("cp_name")
    private String cpName;

    @JsonProperty("user_level")
    private int userLevel;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("series_name")
    private String seriesName;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("is_movie")
    private int isMovie;

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public int getSeriesTotal() {
        return seriesTotal;
    }

    public void setSeriesTotal(int seriesTotal) {
        this.seriesTotal = seriesTotal;
    }

    public List<String> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<String> tagIds) {
        this.tagIds = tagIds;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getUpdateCycleDescription() {
        return updateCycleDescription;
    }

    public void setUpdateCycleDescription(String updateCycleDescription) {
        this.updateCycleDescription = updateCycleDescription;
    }

    public String getCpName() {
        return cpName;
    }

    public void setCpName(String cpName) {
        this.cpName = cpName;
    }

    public int getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(int userLevel) {
        this.userLevel = userLevel;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getIsMovie() {
        return isMovie;
    }

    public void setIsMovie(int isMovie) {
        this.isMovie = isMovie;
    }
}
