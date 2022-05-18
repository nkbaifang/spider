package io.ziyi.spider.viu.model;

import io.ziyi.spider.viu.vo.ViuSeriesSummary;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(
        name = "series",
        indexes = {
                @Index(name = "IDX_series_cp", columnList = "cp_name"),
                @Index(name = "IDX_series_language", columnList = "language")
        }
)
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "query_series_without_products",
                query = "select S.* from series S left join series_product P on S.id = P.series_id where P.series_id is null order by S.id asc",
                resultClass = Series.class
        )
})
public class Series extends BaseModel<Long> {

    @Id
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false, length = 80)
    private String name;

    @Column(name = "cp_name", length = 40)
    private String cpName;

    @Column(name = "is_movie", nullable = false)
    private int isMovie;

    @Column(name = "product_total", nullable = false)
    private int productTotal;

    @Column(name = "language", length = 32)
    private String language;

    @Column(name = "release_time", columnDefinition = "timestamp(3) default null")
    private Date releaseTime;

    @Column(name = "allow_tv", nullable = false)
    private int allowTv;

    @Column(name = "last_product_id", nullable = false)
    private long lastProductId;

    public Series() {
    }

    public Series(ViuSeriesSummary summary) {
        this.id = summary.getSeriesId();
        this.name = summary.getName();
        this.cpName = summary.getCpName();
        this.isMovie = summary.getIsMovie();
        this.productTotal = summary.getProductTotal();
        this.lastProductId = summary.getProductId();
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public int getAllowTv() {
        return allowTv;
    }

    public void setAllowTv(int allowTv) {
        this.allowTv = allowTv;
    }

    public long getLastProductId() {
        return lastProductId;
    }

    public void setLastProductId(long lastProductId) {
        this.lastProductId = lastProductId;
    }
}
