package io.ziyi.spider.util;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class MapUtils {

    public static Map<String, Object> common() {
        return new LinkedHashMap<>();
    }

    public static <K, V> Map<K, V> build(K[] keys, V[] values) {
        Objects.requireNonNull(keys);
        Objects.requireNonNull(values);

        if ( keys.length != values.length ) {
            throw new IllegalArgumentException("Value count must match the count of keys.");
        }

        Map<K, V> result = new LinkedHashMap<>();
        for ( int i = 0; i < keys.length; i++ ) {
            K key = keys[i];
            V value = values[i];
            result.put(key, value);
        }
        return result;
    }

    public static <K, V> Map<K, V> singleMap(K key, V value) {
        Map<K, V> map = new LinkedHashMap<>();
        map.put(key, value);
        return Collections.unmodifiableMap(map);
    }

    /**
     * Build a map object.
     *
     * @param args String key1, Object value1, String key2, Object value2, ...
     * @return map object
     */
    public static Map<String, Object> build(Object... args) {
        Objects.requireNonNull(args);
        if ( args.length % 2 != 0 ) {
            throw new IllegalArgumentException("Value count must match the count of keys.");
        }

        Map<String, Object> result = common();
        for ( int i = 0; i < args.length; i += 2 ) {
            if ( !(args[i] instanceof String) ) {
                throw new IllegalArgumentException("Key must be string: index=" + i);
            }
            String key = args[i].toString();
            Object value = args[i + 1];
            result.put(key, value);
        }
        return result;
    }

    @SafeVarargs
    public static <K, V> Map<K, V> merge(Map<K, V> one, Map<K, V>... others) {
        Objects.requireNonNull(one);
        Map<K, V> result = new LinkedHashMap<>(one);
        for ( Map<K, V> other : others ) {
            if ( other != null ) {
                result.putAll(other);
            }
        }
        return result;
    }
}