package io.ziyi.spider.viu.model;

import io.ziyi.spider.viu.vo.ViuProduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "series_product",
        indexes = {
                @Index(name = "IDX_series_product_series", columnList = "series_id"),
                @Index(name = "IDX_series_product_number", columnList = "number"),
                @Index(name = "IDX_series_product_ccs", columnList = "ccs_product_id"),
                @Index(name = "IDX_series_product_error", columnList = "stream_error")
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "query_products_without_streams",
                query = "select P.* from series_product P left join series_stream S on P.id = S.product_id where S.product_id is null order by P.series_id asc, P.number asc",
                resultClass = SeriesProduct.class
        )
})
public class SeriesProduct extends BaseModel<Long> {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "series_id", nullable = false)
    private long seriesId;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "synopsis", length = 240)
    private String synopsis;

    @Column(name = "ccs_product_id", length = 40)
    private String ccsProductId;

    @Column(name = "share_url", length = 240)
    private String shareUrl;

    @Column(name = "stream_error", nullable = false)
    private int streamError;

    public SeriesProduct() {
    }

    public SeriesProduct(ViuProduct product) {
        this.id = product.getProductId();
        this.seriesId = product.getSeriesId();
        this.number = product.getNumber();
        this.synopsis = product.getSynopsis();
        this.ccsProductId = product.getCcsProductId();
        this.shareUrl = product.getShareUrl();
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
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

    public String getShareUrl() {
        return shareUrl;
    }

    public void setShareUrl(String shareUrl) {
        this.shareUrl = shareUrl;
    }

    public int getStreamError() {
        return streamError;
    }

    public void setStreamError(int streamError) {
        this.streamError = streamError;
    }
}
