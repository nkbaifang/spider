package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.util.MapUtils;
import io.ziyi.spider.viu.model.ProductStream;
import io.ziyi.spider.viu.model.SeriesProduct;
import io.ziyi.spider.viu.vo.ViuResponse;
import io.ziyi.spider.viu.vo.ViuStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class StreamService extends BaseService {

    private static final String STREAM_KEY = "spider.viu.series.stream-list";

    public StreamService() {
        super("stream-service");
    }

    @Override
    public void afterPropertiesSet() {
        schedule(this::querySeriesStream, 2000L, 5000L);
    }

    public void querySeriesStream() {
        String value = peekTaskValue(STREAM_KEY);
        if ( value == null ) {
            //logger.info("Query product stream", "No products found.");
            return;
        }

        int batch = ConfigTools3.getInt("spider.viu.series.query.batch-maximum-count", 6);
        int count = 0;
        StreamService service = findBean(StreamService.class);
        ViuSpider spider = new ViuSpider(false);
        while ( value != null ) {
            String[] ss = value.split(":", 3);
            long productId = Long.parseLong(ss[0]);
            String ccsProductId = ss[1];
            String shareUrl = ss[2];

            logger.info("Query product streams", "Ready. productId={}, ccsProductId={}", productId, ccsProductId);

            try {
                ViuResponse<Map<String, Object>> response = spider.findProductStreams(shareUrl, ccsProductId);
                if ( response == null ) {
                    service.saveProductStreamError(productId, null);
                } else if ( response.ok() ) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> stream = (Map<String, Object>) response.getData().get("stream");
                    service.saveProductStream(productId, stream);
                } else {
                    service.saveProductStreamError(productId, response.getStatus());
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Query product streams", "Failed to query product streams. productId={}", productId);
            }

            if ( ++count >= batch ) {
                break;
            }

            value = peekTaskValue(STREAM_KEY);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    protected void saveProductStreamError(long productId, ViuStatus status) {
        SeriesProduct product = dao.find(SeriesProduct.class, productId, true);
        if ( product == null ) {
            logger.warn("Update series product", "Cannot find product. id={}", productId);
            return;
        }

        product.setStreamError(status == null ? -1 : status.getCode());
        dao.saveSeriesProduct(product);
    }

    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    protected void saveProductStream(long productId, Map<String, Object> data) {
        if ( data == null ) {
            logger.warn("Save product streams", "Empty stream: productId={}", productId);
            return;
        }

        String region = (String) data.get("region");
        String ispName = (String) data.get("ispName");
        Map<String, ProductStream> streamMap = new LinkedHashMap<>();
        Map<String, Number> sizeMap = (Map<String, Number>) data.get("size");
        if ( sizeMap != null ) {
            sizeMap.forEach((res, size) -> {
                ProductStream stream = new ProductStream();
                stream.setProductId(productId);
                stream.setRegion(region);
                stream.setIspName(ispName);
                stream.setResolution(res);
                stream.setSize(size.longValue());
                streamMap.put(res, stream);
            });
        }

        Map<String, String> urlMap = (Map<String, String>) MapUtils.optionalSearch(data, "url4", "url3", "url2", "urlMap");
        if ( urlMap != null ) {
            urlMap.forEach((res, url) -> {
                ProductStream stream = streamMap.get(res);
                if ( stream == null ) {
                    stream = new ProductStream();
                    stream.setProductId(productId);
                    stream.setRegion(region);
                    stream.setIspName(ispName);
                    stream.setResolution(res);
                }
                stream.setM3u8Url(url);
            });
        }

        Collection<ProductStream> streams = streamMap.values();
        logger.info("Save product streams", "Product: id={}, count={}", productId, streams.size());
        dao.updateProductStreams(productId, streams);
    }

    private void refreshProducts(List<SeriesProduct> products) {
        List<String> values = products.stream()
                .map(product -> String.format("%d:%s:%s", product.getId(), product.getCcsProductId(), product.getShareUrl()))
                .collect(Collectors.toList());
        long a = appendTaskValue(STREAM_KEY, values);
        logger.info("Refresh product streams", "Found streams: count={}, a={}", values.size(), a);
    }

    public int refreshBySeries(long seriesId) {
        List<SeriesProduct> products = dao.findSeriesProducts(seriesId);
        refreshProducts(products);
        return products.size();
    }

    public int refreshBySeries() {
        List<SeriesProduct> products = dao.findProductsWithoutStreams();
        refreshProducts(products);
        return products.size();
    }

    public int refreshByProduct(long productId) {
        SeriesProduct product = dao.findProduct(productId);
        if ( product == null ) {
            return 0;
        }
        refreshProducts(Collections.singletonList(product));
        return 1;
    }
}
