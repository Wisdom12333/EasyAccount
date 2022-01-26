package com.shirj.pub.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;


/**
 * Utils for the {@code Map}.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public final class MapUtils {

    /**
     * Check if a {@code Map} is empty or {@code null}.
     *
     * @param map {@code Map},may be {@code null}.
     * @return true if the {@code Map} is empty or {@code null}.
     */
    public static boolean isEmpty(final Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * Check if a {@code Map} is not empty and not {@code null}.
     *
     * @param map {@code Map},may be {@code null}.
     * @return true if the {@code Map} is not empty and not {@code null}.
     */
    public static boolean isNotEmpty(final Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * Get value from a {@code Map} by the key.
     *
     * @param map          {@code Map}, may be {@code null}.
     * @param key          {@code String}, key of map, may not be contained.
     * @param defaultValue {@code String}, when map is not contains the key or the value is blank.
     * @return The value of map or the defaultValue.
     */
    public static String getValue(final Map<?, ?> map, final String key, final String defaultValue) {
        if (isEmpty(map)) {
            return defaultValue;
        }
        String value = String.valueOf(map.get(key));
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

    /**
     * Get value from a {@code Map} by the key.
     *
     * @param map {@code Map}, may be {@code null}.
     * @param key {@code String}, key of map, may not be contained.
     * @return The value of map or an empty string.
     */
    public static String getValue(final Map<?, ?> map, final String key) {
        if (isEmpty(map)) {
            return "";
        }
        String value = String.valueOf(map.get(key));
        if (StringUtils.isBlank(value)) {
            return "";
        }
        return value;
    }

}

