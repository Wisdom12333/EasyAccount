package com.shirj.svc.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;


/**
 * <p>Utils for the {@code Map}.</p>
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public final class MapUtils {

    /**
     * <p>Check if a {@code Map} is empty or {@code null}.</p>
     *
     * @param map {@code Map},may be {@code null}.
     * @return true if the {@code Map} is empty or {@code null}.
     */
    public static boolean isEmpty(Map<?, ?> map) {
        return null == map || map.isEmpty();
    }

    /**
     * <p>Check if a {@code Map} is not empty and not {@code null}.</p>
     *
     * @param map {@code Map},may be {@code null}.
     * @return true if the {@code Map} is not empty and not {@code null}.
     */
    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    /**
     * <p>Get value from a {@code Map} by the key.</p>
     *
     * @param map          {@code Map}, may be {@code null}.
     * @param key          {@code String}, key of map, may not be contained.
     * @param defaultValue {@code String}, when map is not contains the key or the value is blank.
     * @return The value of map or the defaultValue.
     */
    public static String getValue(Map<?, ?> map, String key, String defaultValue) {
        if (isEmpty(map)) {
            return defaultValue;
        }
        String value = String.valueOf(map.get(key));
        if (StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value;
    }

}

