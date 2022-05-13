package io.ziyi.spider.viu.service;

import com.fasterxml.jackson.core.type.TypeReference;
import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.http.HttpClient;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.util.MapUtils;
import io.ziyi.spider.viu.vo.ViuResponse;
import io.ziyi.spider.viu.vo.ViuSeries;
import io.ziyi.spider.viu.vo.ViuSeriesStat;
import io.ziyi.spider.viu.vo.ViuStatus;

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
                .addQuery("cpreference_id", "")
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
/*
    public Item queryMovieDetail(String id) {
        HttpClient.Request request = new HttpClient.RequestBuilder()
                .url("https://api.showmax.com/v142.0/website/catalogue/asset/" + id)
                .addQuery("lang", "eng")
                .addQuery("subscription_status", "full")
                .get();
        Item item = null;
        try {
            HttpClient.Response<Item> response = client.execute(request, Item.TYPE);
            if ( response.ok() ) {
                item = response.data();
            } else {
                logger.warn("Query movie detail", "Result: id={}, code={}", id, response.code());
            }
        } catch ( IOException ex ) {
            logger.error(ex, "Query movie detail", "Failed: id={}", id);
        }
        logger.info("Query movie", "Detail: id={}", id);
        return item;
    }

    public boolean downloadVideoMPD(String videoId, File target) throws Exception {
        HttpClient.Request ur = new HttpClient.RequestBuilder()
                .url("https://api.showmax.com/v142.0/website/playback/play/" + videoId)
                .addQuery("encoding", "mpd_widevine_modular")
                .addQuery("lang", "eng")
                .addQuery("subscription_status", "full")
                .get();
        Play play;
        try {
            HttpClient.Response<Play> response = client.execute(ur, Play.TYPE);
            if ( response.ok() ) {
                play = response.data();
            } else {
                logger.warn("Download video MPD", "Failed to download mpd. id={}, code={}", videoId, response.code());
                return false;
            }
        } catch ( IOException ex ) {
            logger.warn(ex, "Download video MPD", "Failed to download mpd. id={}", videoId);
            return false;
        }

        List<String> urls = play.getUrls();
        if ( urls == null || urls.isEmpty() ) {
            logger.warn("Download video MPD", "Empty MPD urls. id={}", videoId);
            return false;
        }

        boolean downloaded = false;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        for ( String url : urls ) {
            logger.info("Download video MPD", "Detail: video={}, url={}", videoId, url);
            HttpClient.Response<Long> response = client.download(url, bout);
            long length;
            if ( response.ok() ) {
                length = response.data();
            } else {
                length = -1;
                logger.warn("Download video MPD", "Invalid length. id={}, code={}, length={}", videoId, response.code(), length);
            }
            if ( length > 0L ) {
                downloaded = true;
                break;
            }
        }
        boolean ok = false;
        if ( downloaded ) {
            byte[] data = bout.toByteArray();
            try ( FileOutputStream fout = new FileOutputStream(target) ) {
                fout.write(data);
                ok = true;
            }
        } else {
            logger.warn("Download video MPD", "All urls failed. id={}", videoId);
        }
        return ok;
    }
*/
    public void searchSeries(long categoryId, Consumer<List<ViuSeries>> consumer) throws Exception {
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
                List<ViuSeries> series = response.getData().getSeries();
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

}
