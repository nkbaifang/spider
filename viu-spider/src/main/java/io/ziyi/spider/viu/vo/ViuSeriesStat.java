package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuSeriesStat extends CommonVO {

    @JsonProperty("category_series_total")
    private List<ViuCategorySeriesTotal> categorySeriesTotal;

    @JsonProperty("series")
    private List<ViuSeries> series;

    public List<ViuCategorySeriesTotal> getCategorySeriesTotal() {
        return categorySeriesTotal;
    }

    public void setCategorySeriesTotal(List<ViuCategorySeriesTotal> categorySeriesTotal) {
        this.categorySeriesTotal = categorySeriesTotal;
    }

    public List<ViuSeries> getSeries() {
        return series;
    }

    public void setSeries(List<ViuSeries> series) {
        this.series = series;
    }
}
