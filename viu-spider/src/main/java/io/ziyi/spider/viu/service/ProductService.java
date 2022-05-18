package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.viu.model.Series;
import io.ziyi.spider.viu.model.SeriesProduct;
import io.ziyi.spider.viu.vo.ViuProduct;
import io.ziyi.spider.viu.vo.ViuProductSummary;
import io.ziyi.spider.viu.vo.ViuSeries;
import io.ziyi.spider.viu.vo.ViuVodDetail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductService extends BaseService {

    private static final String PRODUCT_DETAIL_KEY = "spider.viu.series.product-detail-list";

    private static final String PRODUCT_KEY = "spider.viu.series.product-list";

    public ProductService() {
        super("product-service");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        schedule(this::queryProductDetail, 2300L, 4300L);
        schedule(this::querySeriesProduct, 2000L, 5000L);
    }

    //@Scheduled(fixedDelay = 4300L, initialDelay = 2300L)
    public void queryProductDetail() {
        String value = peekTaskValue(PRODUCT_DETAIL_KEY);
        if ( value == null ) {
            //logger.info("Query series stream", "No series found.");
            return;
        }

        ProductService service = findBean(ProductService.class);
        ViuSpider spider = new ViuSpider(true);
        while ( value != null ) {
            String[] ss = value.split(":");
            long productId = Long.parseLong(ss[0]);
            int retry = Integer.parseInt(ss[1]);

            boolean ok = false;

            try {
                ViuVodDetail detail = spider.findProductDetail(productId);
                if ( detail != null ) {
                    ViuProduct product = detail.getCurrentProduct();
                    service.saveProducts(product);
                    ok = true;
                } else {
                    logger.warn("Query product detail", "Empty vod detail. productId={}, retry={}", productId, retry);
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Query product detail", "Failed to query series product.productId={}, retry={}", productId, retry);
            }

            if ( !ok ) {
                if ( retry < 2 ) {
                    appendTaskValue(PRODUCT_DETAIL_KEY, String.format("%d:%d", productId, retry + 1));
                } else {
                    logger.warn("Query product detail", "Too many failures. productId={}, retry={}", productId, retry);
                }
            }

            value = peekTaskValue(PRODUCT_DETAIL_KEY);
        }
    }

    //@Scheduled(fixedDelay = 5000L, initialDelay = 2000L)
    public void querySeriesProduct() {
        String value = peekTaskValue(PRODUCT_KEY);
        if ( value == null ) {
            //logger.info("Query series product", "No products found.");
            return;
        }

        final int maxRetryCount = ConfigTools3.getInt("spider.viu.product.maximum-retry", 2);

        ProductService service = findBean(ProductService.class);
        ViuSpider spider = new ViuSpider(true);
        while ( value != null ) {
            String[] ss = value.split(":");
            long productId = Long.parseLong(ss[0]);
            int retry = Integer.parseInt(ss[1]);

            boolean ok = false;

            try {
                ViuVodDetail detail = spider.findProductDetail(productId);
                if ( detail != null ) {
                    ViuSeries series = detail.getSeries();
                    service.updateSeries(series);
                    ok = true;
                } else {
                    logger.warn("Query series product", "Empty vod detail. productId={}, retry={}", productId, retry);
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Query series product", "Failed to query series product. product={}, retry={}", productId, retry);
            }

            if ( !ok ) {
                if ( retry < maxRetryCount ) {
                    appendTaskValue(PRODUCT_KEY, String.format("%d:%d", productId, retry + 1));
                } else {
                    logger.warn("Query series product", "Too many failures. product={}, retry={}", productId, retry);
                }
            }

            value = peekTaskValue(PRODUCT_KEY);
        }
    }

    protected void updateSeries(ViuSeries vo) {
        if ( vo == null ) {
            logger.warn("Update series", "Empty vo");
            return;
        }

        Series series = dao.findSeries(vo.getSeriesId());
        if ( series == null ) {
            logger.warn("Update series", "Cannot find series, id={}", vo.getSeriesId());
            return;
        }

        series.setAllowTv(vo.getAllowTv());
        series.setLanguage(vo.getSeriesLanguage());
        series.setReleaseTime(new Date(vo.getReleaseTime() * 1000L));
        dao.saveSeries(series);

        List<ViuProductSummary> products = vo.getProducts();
        if ( products != null ) {
            List<String> values = products.stream().map(product -> String.format("%d:0", product.getProductId())).collect(Collectors.toList());
            appendTaskValue(PRODUCT_DETAIL_KEY, values);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    protected void saveProducts(ViuProduct vo) {
        if ( vo == null ) {
            logger.warn("Save series with product", "Empty vo");
            return;
        }

        SeriesProduct product = dao.find(SeriesProduct.class, vo.getProductId(), true);
        if ( product == null ) {
            product = new SeriesProduct(vo);
        } else {
            product.setCcsProductId(vo.getCcsProductId());
            product.setShareUrl(vo.getShareUrl());
        }
        dao.saveSeriesProduct(product);
    }

    public int refreshProducts(long seriesId) {
        Series series = dao.findSeries(seriesId);
        if ( series == null ) {
            return 0;
        }

        appendTaskValue(PRODUCT_KEY, String.format("%d:0", series.getLastProductId()));
        return 1;
    }

    public int refreshProducts() {
        List<Series> list = dao.findSeriesWithoutProducts();
        List<String> values = list.stream().map(series -> String.format("%d:0", series.getLastProductId())).collect(Collectors.toList());
        appendTaskValue(PRODUCT_KEY, values);
        return list.size();
    }
}
