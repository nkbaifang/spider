package io.ziyi.spider.viu.model;

import java.io.Serializable;
import java.util.Objects;

public class SeriesCategoryPK implements Serializable {

    private static final long serialVersionUID = 0x220ca43f1ccdce51L;

    private long seriesId;

    private long categoryId;

    public SeriesCategoryPK() {
    }

    public SeriesCategoryPK(long seriesId, long categoryId) {
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
    public boolean equals(Object other) {
        if ( this == other ) {
            return true;
        }
        if ( other == null ) {
            return false;
        }
        if ( other.getClass() != this.getClass() ) {
            return false;
        }

        SeriesCategoryPK that = (SeriesCategoryPK) other;
        return seriesId == that.seriesId && categoryId == that.categoryId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), seriesId, categoryId);
    }
}
