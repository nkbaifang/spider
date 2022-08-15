package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.viu.model.ProductStream;
import io.ziyi.spider.viu.model.SeriesProduct;
import io.ziyi.spider.viu.utils.FileUtils;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class M3uService extends BaseService {

    private static final String M3U8_KEY = "spider.viu.stream.m3u8.tasks";
    private static final String M3U8_LOCK = "spider.viu.stream.m3u8.lock";

    public M3uService() {
        super("m3u-service");
    }

    @Override
    public void afterPropertiesSet() {
        schedule(this::checkTasks, 2000L, 5000L);
        schedule(this::downloadStream, 1700L, 1000L);
    }

    private void checkTasks() {
        boolean acquired = acquiredLock(M3U8_LOCK, 2000L);
        logger.info("Check m3u8 tasks", "Acquired: {}", acquired);
        if ( acquired ) {
            int size = getScoredTaskListSize(M3U8_KEY);
            logger.info("Check m3u8 tasks", "Tasks: queue={}", size);
            if ( size < 100 ) {
                appendNewTasks();
            }
        }
    }

    private void downloadStream() {
        String value = peekScoredTaskValue(M3U8_KEY);
        if ( value == null ) {
            //logger.info("Download stream url", "No streams found.");
            return;
        }

        M3uService service = findBean(M3uService.class);
        ViuSpider spider = new ViuSpider(false);
        while ( value != null ) {
            String[] ss = value.split(":", 3);
            long productId = Long.parseLong(ss[0]);
            long streamId = Long.parseLong(ss[1]);
            String m3u8Url = ss[2];

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            try {
                long size = spider.download(m3u8Url, bout);
                if ( size > 0L ) {
                    service.updateStream(productId, streamId, bout.toByteArray());
                } else {
                    service.updateStream(streamId, -1);
                }
            } catch ( Exception ex ) {
                logger.warn(ex, "Download stream url", "Failed to download stream. product={}, stream={}", productId, streamId);
                service.updateStream(streamId, -5);
            }

            value = peekScoredTaskValue(M3U8_KEY);
        }
    }

    private void appendNewTasks() {
        boolean enabled = ConfigTools3.getBoolean("spider.viu.streams.task.append.enabled", true);
        logger.info("Apppend m3u8 tasks", "Enabled: {}", enabled);
        if ( !enabled ) {
            return;
        }

        int count = ConfigTools3.getInt("spider.viu.streams.task.append.maximum-count", 100);
        List<ProductStream> list = dao.searchAndUpdateStreams(ProductStream.Status.ready, ProductStream.Status.downloading, count);
        logger.info("Apppend m3u8 tasks", "Append: count={}", list.size());
        prepareStreamsToDownload(list);
    }

    private void prepareStreamsToDownload(List<ProductStream> list) {
        Set<ZSetOperations.TypedTuple<String>> tuples = list.stream().map(stream -> {
            String value = String.format("%d:%d:%s", stream.getProductId(), stream.getId(), stream.getM3u8Url());
            return new DefaultTypedTuple<>(value, stream.getId().doubleValue());
        }).collect(Collectors.toSet());
        super.appendScoredTaskValue(M3U8_KEY, tuples);
    }

    protected void updateStream(long streamId, int error) {
        ProductStream stream = dao.findProductStream(streamId);
        if ( stream != null ) {
            stream.setStreamError(error);
            stream.setStatus(ProductStream.Status.download_failed);
            dao.saveProductStream(stream);
        }
    }

    protected void updateStream(long productId, long streamId, byte[] data) {
        File file = FileUtils.createLocalFile(String.format("/temp/%d.m3u8", streamId));
        boolean ok;
        try ( FileOutputStream fout = new FileOutputStream(file) ) {
            fout.write(data);
            ok = true;
        } catch ( IOException ex ) {
            logger.warn(ex, "Update stream", "Failed to save file. product={}, stream={}, file={}", productId, streamId, file);
            ok = false;
        }

        ProductStream stream = dao.findProductStream(streamId);
        if ( stream == null ) {
            logger.warn("Update stream", "Stream not found. id={}", streamId);
            FileUtils.deleteFile(file);
        } else {
            SeriesProduct product = dao.findProduct(productId);
            if ( product == null ) {
                logger.warn("Update stream", "Product not found. id={}", productId);
                FileUtils.deleteFile(file);
                stream.setStatus(ProductStream.Status.download_failed);
            } else if ( ok ) {
                String localPath = String.format("/series/%d/%d-%d/%s.m3u8", product.getSeriesId(), product.getNumber(), product.getId(), stream.getResolution());
                File localFile = FileUtils.createLocalFile(localPath);
                FileUtils.moveFile(file, localFile);
                stream.setLocalPath(localPath);
                stream.setStatus(ProductStream.Status.downloaded);
                stream.setStreamError(0);
            } else {
                stream.setStatus(ProductStream.Status.download_failed);
                stream.setStreamError(-2);
            }
            dao.saveProductStream(stream);
        }
    }

    public int refreshByProduct(long productId) {
        List<ProductStream> list = dao.findProductStreams(productId);
        prepareStreamsToDownload(list);
        return list.size();
    }

    public int refreshBySeries(long seriesId) {
        List<SeriesProduct> products = dao.findSeriesProducts(seriesId);
        List<ProductStream> streams = products.stream()
                .flatMap(product -> dao.findProductStreams(product.getId()).stream())
                .collect(Collectors.toList());
        prepareStreamsToDownload(streams);
        return streams.size();
    }

    public int getTasksCount() {
        int size = getScoredTaskListSize(M3U8_KEY);
        logger.info("Tasks count", "Count: {}", size);
        return size;
    }
}
