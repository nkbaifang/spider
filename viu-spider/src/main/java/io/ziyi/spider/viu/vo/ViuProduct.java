package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuProduct extends CommonVO {

    private static final long serialVersionUID = 0xf33d27942cd782f3L;

    @JsonProperty("series_id")
    private long seriesId;

    @JsonProperty("product_id")
    private long productId;

    @JsonProperty("number")
    private int number;

    @JsonProperty("synopsis")
    private String synopsis;

    @JsonProperty("ccs_product_id")
    private String ccsProductId;

    @JsonProperty("allow_download")
    private int allowDownload;

    @JsonProperty("released_product_total")
    private int releasedProductTotal;

    @JsonProperty("share_url")
    private String shareUrl;

    @JsonProperty("subtitle")
    private List<ViuSubtitle> subtitles;

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

    public String getCcsProductId() {
        return ccsProductId;
    }

    public void setCcsProductId(String ccsProductId) {
        this.ccsProductId = ccsProductId;
    }

    public int getAllowDownload() {
        return allowDownload;
    }

    public void setAllowDownload(int allowDownload) {
        this.allowDownload = allowDownload;
    }

    public int getReleasedProductTotal() {
        return releasedProductTotal;
    }

    public void setReleasedProductTotal(int releasedProductTotal) {
        this.releasedProductTotal = releasedProductTotal;
    }

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public List<ViuSubtitle> getSubtitles() {
        return subtitles;
    }

    public void setSubtitles(List<ViuSubtitle> subtitles) {
        this.subtitles = subtitles;
    }
}
