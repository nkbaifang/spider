package io.ziyi.spider.viu.service;

import io.ziyi.spider.viu.model.Category;
import io.ziyi.spider.viu.model.Series;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SeriesService extends BaseService {

    private static final String PRODUCT_KEY = "spider.viu.series.product-list";

    public SeriesService() {
        super("series-service");
    }

    private void refreshSeries(ViuSpider spider, long categoryId) {
        logger.info("Refresh series", "Start refreshing series. category={}", categoryId);
        try {
            spider.searchSeries(categoryId, summaries -> {
                logger.info("Search series", "Found series: count={}", summaries.size());
                summaries.forEach(summary -> {
                    Series series = new Series(summary);
                    dao.saveSeries(series, categoryId);
                    //appendTaskValue(PRODUCT_KEY, String.format("%d:%d", summary.getProductId(), 0));
                    //appendSeriesToGetProduct(summary.getSeriesId(), summary.getProductId(), 0);
                });
            });
        } catch ( Throwable error ) {
            logger.error(error, "Refresh series failed", "");
        }
        logger.info("Refresh series", "Finished refreshing series.");
    }

    public void refreshSeries(long categoryId) {
        boolean acquired = lockedAction("spider.viu.series.refresh", () -> {
            logger.info("Refresh series", "Start refreshing series.");
            executeAsyncAction(() -> {
                ViuSpider spider = new ViuSpider(true);
                refreshSeries(spider, categoryId);
                releaseActionLock("spider.viu.series.refresh");
            });
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh series", "Process already started.");
        }
    }

    public void refreshSeries() {
        boolean acquired = lockedAction("spider.viu.series.refresh", () -> {
            logger.info("Refresh series", "Start refreshing series.");
            List<Category> categories = dao.findCategories();
            executeAsyncAction(() -> {
                ViuSpider spider = new ViuSpider(false);
                categories.forEach(category -> {
                    refreshSeries(spider, category.getId());
                });
                releaseActionLock("spider.viu.series.refresh");
            });
            return false;
        });
        if ( !acquired ) {
            logger.warn("Refresh movie", "Process already started.");
        }
    }
}
