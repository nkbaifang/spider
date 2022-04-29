package io.ziyi.spider.showmax.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.http.HttpClient;
import io.ziyi.spider.http.HttpException;
import io.ziyi.spider.showmax.utils.FileUtils;
import io.ziyi.spider.showmax.vo.Asset;
import io.ziyi.spider.showmax.vo.Item;
import io.ziyi.spider.showmax.vo.Play;
import io.ziyi.spider.util.CommonLogger;
import io.ziyi.spider.util.MapUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class ShowmaxSpider {

    private static final CommonLogger logger = CommonLogger.getLogger(ShowmaxSpider.class);

    private final HttpClient client;

    public ShowmaxSpider() {
        Map<String, String> headers = MapUtils.singleMap("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36");
        Proxy proxy = null;
        boolean useProxy = ConfigTools3.getBoolean("spider.showmax.http.proxy", false);
        if ( useProxy ) {
            String host = ConfigTools3.getString("spider.showmax.http.proxy.host");
            int port = ConfigTools3.getInt("spider.showmax.http.proxy.port");
            try {
                proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(InetAddress.getByName(host), port));
            } catch ( UnknownHostException ex ) {
                logger.error(ex, "Proxy error", "Unknown proxy host: {}", host);
            }
        }

        long connectTimeout = ConfigTools3.getLong("spider.showmax.http.connect-timeout-seconds", 20L);
        long readTimeout = ConfigTools3.getLong("spider.showmax.http.connect-timeout-seconds", 30L);
        this.client = new HttpClient(headers, proxy, connectTimeout, readTimeout);
    }

    public Asset queryAssets(String lang, String type, int start, int num) throws Exception {
        HttpClient.Request request = new HttpClient.RequestBuilder()
                .url("https://api.showmax.com/v142.0/website/catalogue/assets")
                .addQuery("field[]", "categories", "description", "images", "number", "season", "section", "start_at", "end_at", "title", "tv_series", "type", "videos", "website_url", "rating", "count_seasons", "year", "coming_soon")
                .addQuery("lang", lang)
                .addQuery("mode_action", "filter")
                .addQuery("type", type)
                .addQuery("reverse", "false")
                .addQuery("start", String.valueOf(start))
                .addQuery("num", String.valueOf(num))
                .get();
        Asset asset = client.execute(request, Asset.TYPE);
        logger.info("Query assets", "Result: lang={}, type={}, start={}, num={}, count={}, total={}, remaining={}", lang, type, start, num, asset.getCount(), asset.getTotal(), asset.getRemaining());
        return asset;
    }

    public Item queryMovieDetail(String id) {
        HttpClient.Request request = new HttpClient.RequestBuilder()
                .url("https://api.showmax.com/v142.0/website/catalogue/asset/" + id)
                .addQuery("lang", "eng")
                .addQuery("subscription_status", "full")
                .get();
        Item item = null;
        try {
            item = client.execute(request, Item.TYPE);
        } catch ( HttpException ex ) {
            logger.error(ex, "Failed to query movie detail", "Movie: id={}", id);
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
            play = client.execute(ur, Play.TYPE);
        } catch ( HttpException ex ) {
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
            long length = client.download(url, bout);
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

    public void searchMovies(Consumer<List<Item>> consumer) throws Exception {
        int start = 0;
        int num = 60;
        boolean failed = false;
        do {
            Asset asset = queryAssets("eng", "movie", start, num);
            if ( asset.getCount() > 0 ) {
                try {
                    consumer.accept(asset.getItems());
                } catch ( Throwable error ) {
                    failed = true;
                    logger.error(error, "Failed to process movies", "Items: start={}, num={}, count={}", start, num, asset.getCount());
                    break;
                }
            }
            if ( asset.getRemaining() > 60 ) {
                start += 60;
                num = 60;
            } else {
                start += asset.getRemaining();
                num = asset.getRemaining();
            }
        } while ( num > 0 );
        logger.info("Finished searching movies", "Result: end={}, failed={}", start, failed);
    }

}
