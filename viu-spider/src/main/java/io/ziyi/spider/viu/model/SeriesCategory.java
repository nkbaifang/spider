package io.ziyi.spider.viu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(
        name = "series_category",
        indexes = {
                @Index(name = "IDX_series_category_series", columnList = "series_id"),
                @Index(name = "IDX_series_category_category", columnList = "category_id")
        }
)
@IdClass(SeriesCategoryPK.class)
public class SeriesCategory extends BaseModel<SeriesCategoryPK> {

    @Id
    @Column(name = "series_id", nullable = false)
    private long seriesId;

    @Id
    @Column(name = "category_id", nullable = false)
    private long categoryId;

    public SeriesCategory() {
    }

    public SeriesCategory(long seriesId, long categoryId) {
        this.seriesId = seriesId;
        this.categoryId = categoryId;
    }

    public long getSeriesId() {
        return seriesId;
    }

    public void setSeriesId(long seriesId) {
        this.seriesId = seriesId;
    }

    public long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public SeriesCategoryPK getId() {
        return new SeriesCategoryPK(seriesId, categoryId);
    }

    @Override
    public void setId(SeriesCategoryPK id) {
        Objects.requireNonNull(id);
        this.seriesId = id.getSeriesId();
        this.categoryId = id.getCategoryId();
    }
}
