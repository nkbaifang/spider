package io.ziyi.spider.viu.model;

import io.ziyi.spider.viu.vo.ViuSeries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "series",
        indexes = {
                @Index(name = "IDX_series_product", columnList = "product_id"),
                @Index(name = "IDX_series_number", columnList = "number"),
                @Index(name = "IDX_series_cp", columnList = "cp_name")
        }
)
public class Series extends BaseModel<Long> {

    @Id
    @Column(name = "series_id", nullable = false)
    private long seriesId;

    @Column(name = "product_id", nullable = false)
    private long productId;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "cp_name", length = 40)
    private String cpName;

    @Column(name = "synopsis", length = 100)
    private String synopsis;

    @Column(name = "released_product_total", nullable = false)
    private int releasedProductTotal;

    public Series() {
    }

    public Series(ViuSeries series) {
        this.seriesId = series.getSeriesId();
        this.name = series.getName();
        this.cpName = series.getCpName();
        this.productId = series.getProductId();
        this.synopsis = series.getSynopsis();
        this.releasedProductTotal = series.getReleasedProductTotal();
        this.number = series.getNumber();
    }

    @Override
    public Long getId() {
        return seriesId;
    }

    @Override
    public void setId(Long id) {
        Objects.requireNonNull(id);
        this.seriesId = id;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
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
