package io.ziyi.spider.viu.service;

import com.fasterxml.jackson.core.type.TypeReference;
import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.http.HttpClient;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.util.MapUtils;
import io.ziyi.spider.viu.vo.ViuProduct;
import io.ziyi.spider.viu.vo.ViuResponse;
import io.ziyi.spider.viu.vo.ViuVodDetail;
import io.ziyi.spider.viu.vo.ViuSeriesStat;
import io.ziyi.spider.viu.vo.ViuSeriesSummary;
import io.ziyi.spider.viu.vo.ViuStatus;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ViuSpider {

    private static final CommonLogger logger = CommonLogger.getLogger(ViuSpider.class);

    private final HttpClient client;

    public ViuSpider(boolean useLogger) {
        Map<String, String> headers = MapUtils.singleMap("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36");

        long connectTimeout = ConfigTools3.getLong("spider.viu.http.connect-timeout-seconds", 20L);
        long readTimeout = ConfigTools3.getLong("spider.viu.http.connect-timeout-seconds", 30L);

        HttpClient.ClientBuilder builder = new HttpClient.ClientBuilder()
                .headers(headers)
                .connectTimeout(connectTimeout)
                .readTimeout(readTimeout)
                .useLogger(useLogger);

        boolean useProxy = ConfigTools3.getBoolean("spider.viu.http.proxy", false);
        if ( useProxy ) {
            String host = ConfigTools3.getString("spider.viu.http.proxy.host");
            int port = ConfigTools3.getInt("spider.viu.http.proxy.port");
            try {
                InetAddress address = InetAddress.getByName(host);
                builder.proxy(address, port);
            } catch ( UnknownHostException ex ) {
                logger.error(ex, "Proxy error", "Unknown proxy host: {}", host);
            }
        }

        this.client = builder.build();
    }

    private ViuResponse<ViuSeriesStat> querySeries(String categoryId, int offset, int length) throws Exception {

        TypeReference<ViuResponse<ViuSeriesStat>> type = new TypeReference<ViuResponse<ViuSeriesStat>>() {};

        HttpClient.Request request = new HttpClient.RequestBuilder()
                .url("https://www.viu.com/ott/hk/index.php")
                .addQuery("r", "category/series-category")
                .addQuery("platform_flag_label", "web")
                .addQuery("area_id", "1")
                .addQuery("language_flag_id", "3")
                .addQuery("category_id", categoryId)
                .addQuery("length", String.valueOf(length))
                .addQuery("offset", String.valueOf(offset))
                .get();
        HttpClient.Response<ViuResponse<ViuSeriesStat>> response = client.execute(request, type);
        ViuResponse<ViuSeriesStat> result;
        if ( response.ok() ) {
            result = response.data();
            ViuStatus status = result.getStatus();
            logger.info("Query series", "Result: category={}, offset={}, length={}, code={}, message={}", categoryId, offset, length, status.getCode(), status.getMessage());
        } else {
            logger.warn("Query series", "Result: category={}, offset={}, length={}, code={}", categoryId, offset, length, response.code());
            result = null;
        }
        return result;
    }

    public void searchSeries(long categoryId, Consumer<List<ViuSeriesSummary>> consumer) throws Exception {
        int offset = 0;
        int length = 10;
        boolean failed = false;
        do {
            ViuResponse<ViuSeriesStat> response = querySeries(String.valueOf(categoryId), offset, length);
            if ( response == null ) {
                logger.warn("Failed to query series", "");
                failed = true;
                break;
            }

            if ( response.ok() ) {
                List<ViuSeriesSummary> series = response.getData().getSeries();
                try {
                    consumer.accept(series);
                } catch ( Throwable error ) {
                    failed = true;
                    logger.error(error, "Failed to query series", "Series: categoryId={}, offset={}, length={}", categoryId, offset, length);
                    break;
                }
                if ( series.size() < length ) {
                    break;
                }
            } else {
                failed = true;
                ViuStatus status = response.getStatus();
                logger.warn("Failed to query series", "Status: categoryId={}, offset={}, length={}, code={}, message={}", categoryId, offset, length, status.getCode(), status.getMessage());
                break;
            }
            offset += 10;
        } while ( true );
        logger.info("Finished query series", "Result: end={}, failed={}", offset, failed);
    }

    public ViuVodDetail findProductDetail(long productId) throws Exception {
        HttpClient.Request request = new HttpClient.RequestBuilder()
                .url("https://www.viu.com/ott/hk/index.php")
                .addQuery("area_id", "1")
                .addQuery("language_flag_id", "3")
                //.addQuery("cpreference_id", "")
                .addQuery("r", "vod/ajax-detail")
                .addQuery("platform_flag_label", "web")
                .addQuery("product_id", String.valueOf(productId))
                .addQuery("ut", "1")
                .get();
        HttpClient.Response<ViuResponse<ViuVodDetail>> response = client.execute(request, new TypeReference<ViuResponse<ViuVodDetail>>() {});
        logger.info("Query series product", "Result: product={}, code={}", productId, response.code());
        if ( !response.ok() ) {
            return null;
        }

        ViuResponse<ViuVodDetail> vr = response.data();
        return vr.getData();
    }

    public ViuResponse<Map<String, Object>> findProductStreams(String shareUrl, String ccsProductId) throws Exception {
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        HttpClient.Response<Long> rsp2 = client.download(shareUrl, null, bout);
        logger.info("Query series shared page", "Result: shareUrl={}, ccsProductId={}, code={}", shareUrl, ccsProductId, rsp2.code());
        if ( !rsp2.ok() ) {
            return null;
        }

        Map<String, String> cookies = rsp2.getCookies();
        String token = cookies.get("token");

        HttpClient.Request req3 = new HttpClient.RequestBuilder()
                .url("https://api-gateway-global.viu.com/api/playback/distribute")
                .addQuery("cpreference_id", "")
                .addQuery("ccs_product_id", ccsProductId)
                .addQuery("language_flag_id", "3")
                .header("authorization", "Bearer " + token)
                .get();

        HttpClient.Response<ViuResponse<Map<String, Object>>> rsp3 = client.execute(req3, new TypeReference<ViuResponse<Map<String, Object>>>() {});
        logger.info("Query series stream", "Result: shareUrl={}, ccsProductId={}, token={}, code={}", shareUrl, ccsProductId, token, rsp3.code());
        return rsp3.data();
    }

    public long download(String url, ByteArrayOutputStream bout) throws Exception {
        long result = -1;
        HttpClient.Response<Long> response = client.download(url, bout);
        if ( response.ok() ) {
            result = response.data();
        } else {
            logger.warn("Download", "Failed to download. code={}", response.code());
        }
        return result;
    }
}
