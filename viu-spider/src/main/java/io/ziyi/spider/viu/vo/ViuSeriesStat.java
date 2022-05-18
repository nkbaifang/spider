package io.ziyi.spider.viu.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.ziyi.spider.common.CommonVO;

import java.util.List;

public class ViuSeriesStat extends CommonVO {

    @JsonProperty("category_series_total")
    private List<ViuCategorySeriesTotal> categorySeriesTotal;

    @JsonProperty("series")
    private List<ViuSeriesSummary> series;

    public List<ViuCategorySeriesTotal> getCategorySeriesTotal() {
        return categorySeriesTotal;
    }

    public void setCategorySeriesTotal(List<ViuCategorySeriesTotal> categorySeriesTotal) {
        this.categorySeriesTotal = categorySeriesTotal;
    }

    public List<ViuSeriesSummary> getSeries() {
        return series;
    }

    public void setSeries(List<ViuSeriesSummary> series) {
        this.series = series;
    }
}
