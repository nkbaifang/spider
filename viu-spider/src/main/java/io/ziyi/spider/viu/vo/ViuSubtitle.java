package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

public class ViuSubtitle extends CommonVO {

    private static final long serialVersionUID = 0x54287b3c5369d0b5L;

    @JsonProperty("is_default")
    private int isDefault;

    @JsonProperty("name")
    private String name;

    @JsonProperty("url")
    private String url;

    @JsonProperty("product_subtitle_id")
    private int product_subtitle_id;

    @JsonProperty("product_subtitle_language_id")
    private int productSubtitleLanguageId;

    @JsonProperty("code")
    private String code;

    public int getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(int isDefault) {
        this.isDefault = isDefault;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getProduct_subtitle_id() {
        return product_subtitle_id;
    }

    public void setProduct_subtitle_id(int product_subtitle_id) {
        this.product_subtitle_id = product_subtitle_id;
    }

    public int getProductSubtitleLanguageId() {
        return productSubtitleLanguageId;
    }

    public void setProductSubtitleLanguageId(int productSubtitleLanguageId) {
        this.productSubtitleLanguageId = productSubtitleLanguageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
