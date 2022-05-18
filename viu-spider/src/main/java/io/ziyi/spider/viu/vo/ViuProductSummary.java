package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuProductSummary extends CommonVO {

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("released_product_total")
    private int releasedProductTotal;

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

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public int getReleasedProductTotal() {
        return releasedProductTotal;
    }

    public void setReleasedProductTotal(int releasedProductTotal) {
        this.releasedProductTotal = releasedProductTotal;
    }
}
