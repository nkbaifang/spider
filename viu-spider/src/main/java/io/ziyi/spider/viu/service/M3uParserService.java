package io.ziyi.spider.viu.service;

import common.config.tools.config.ConfigTools3;
import io.ziyi.spider.viu.dao.SeriesDao;
import io.ziyi.spider.viu.model.ProductStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class M3uParserService extends BaseService {

    private static final String PARSER_LOCK = "spider.viu.parser.lock";
    private static final String PARSER_KEY = "spider.viu.parser.tasks";

    @Autowired
    private SeriesDao dao;

    public M3uParserService() {
        super("m3u8-parser");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        schedule(this::checkTasks, 1500L, 3400L);
        schedule(this::parseM3u8Tasks, 1300L, 4000L);
    }

    private void checkTasks() {
        boolean acquired = acquiredLock(PARSER_LOCK, 1500L);
        logger.info("Check m3u8 tasks", "Acquired: {}", acquired);
        if ( acquired ) {
            int size = getScoredTaskListSize(PARSER_KEY);
            logger.info("Check m3u8 tasks", "Tasks: queue={}", size);
            if ( size < 100 ) {
                appendNewTasks();
            }
        }
    }

    private void appendNewTasks() {
        boolean enabled = ConfigTools3.getBoolean("spider.viu.parser.task.append.enabled", true);
        logger.info("Apppend parser tasks", "Enabled: {}", enabled);
        if ( !enabled ) {
            return;
        }

        int count = ConfigTools3.getInt("spider.viu.streams.task.append.maximum-count", 100);
        List<ProductStream> list = dao.searchAndUpdateStreams(ProductStream.Status.downloaded, ProductStream.Status.parsing, count);
        logger.info("Apppend m3u8 tasks", "Append: count={}", list.size());
        prepareStreamsToParse(list);
    }

    private void prepareStreamsToParse(List<ProductStream> list) {
        Set<ZSetOperations.TypedTuple<String>> tuples = list.stream().map(stream -> {
            String value = String.format("%d:%d:%s", stream.getProductId(), stream.getId(), stream.getM3u8Url());
            return new DefaultTypedTuple<>(value, stream.getId().doubleValue());
        }).collect(Collectors.toSet());
        super.appendScoredTaskValue(PARSER_KEY, tuples);
    }

    private void parseM3u8Tasks() {
        String value = peekScoredTaskValue(PARSER_KEY);
        if ( value == null ) {
            //logger.info("Download stream url", "No streams found.");
            return;
        }

        M3uParserService service = findBean(M3uParserService.class);
        while ( value != null ) {
            String[] ss = value.split(":", 3);
            long productId = Long.parseLong(ss[0]);
            long streamId = Long.parseLong(ss[1]);
            String m3u8Url = ss[2];


            value = peekScoredTaskValue(PARSER_KEY);
        }
    }
}
