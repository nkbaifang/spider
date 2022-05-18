package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuVodDetail extends CommonVO {

    @JsonProperty("current_product")
    private ViuProduct currentProduct;

    @JsonProperty("series")
    private ViuSeries series;

    @JsonProperty("is_follow")
    private int isFollow;

    public ViuProduct getCurrentProduct() {
        return currentProduct;
    }

    public void setCurrentProduct(ViuProduct currentProduct) {
        this.currentProduct = currentProduct;
    }

    public ViuSeries getSeries() {
        return series;
    }

    public void setSeries(ViuSeries series) {
        this.series = series;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }
}
