package io.ziyi.spider.viu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "product_stream",
        indexes = {
                @Index(name = "IDX_product_stream_product", columnList = "product_id"),
                @Index(name = "IDX_product_stream_resolution", columnList = "resolution"),
                @Index(name = "UQ_product_stream", columnList = "product_id, resolution", unique = true),
                @Index(name = "IDX_product_stream_region", columnList = "region"),
                @Index(name = "IDX_product_stream_isp", columnList = "isp_name")
        }
)
public class ProductStream extends BaseModel<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "resolution", nullable = false, length = 20)
    private String resolution;

    @Column(name = "size", nullable = false)
    private long size;

    @Column(name = "region", length = 16)
    private String region;

    @Column(name = "isp_name", length = 40)
    private String ispName;

    @Column(name = "m3u8_url", length = 2048)
    private String m3u8Url;

    @Column(name = "stream_error", nullable = false)
    private int streamError;


    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        Objects.requireNonNull(id);
        this.id = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getIspName() {
        return ispName;
    }

    public void setIspName(String ispName) {
        this.ispName = ispName;
    }

    public String getM3u8Url() {
        return m3u8Url;
    }

    public void setM3u8Url(String m3u8Url) {
        this.m3u8Url = m3u8Url;
    }

    public int getStreamError() {
        return streamError;
    }

    public void setStreamError(int streamError) {
        this.streamError = streamError;
    }
}
