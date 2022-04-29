package io.ziyi.spider.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class JsonUtils {

    private static final ObjectMapper mapper;

    private static final TypeReference<LinkedHashMap<String, Object>> mapTypeRef = new TypeReference<LinkedHashMap<String, Object>>() {};

    static {
        mapper = createDefault();
    }

    private static ObjectMapper createDefault() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        mapper.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, true);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        return mapper;
    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static String json(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch ( Exception ex ) {
            return String.valueOf(object);
        }
    }

    public static <T> T parse(String json, Class<T> type) throws Exception {
        if ( json == null || json.isEmpty() ) {
            return null;
        }

        return mapper.readValue(json, type);
    }

    public static <T> T parse(String json, TypeReference<T> type) throws IOException {
        if ( StringUtils.isEmpty(json) ) {
            return null;
        }
        return mapper.readValue(json, type);
    }

    public static Map<String, Object> parseAsMap(String json) throws Exception {
        return parse(json, mapTypeRef);
    }
}