package com.shirj.pub.utils;


import java.util.List;

/**
 * Utils for the arrays.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public final class ArrayUtils {

    /**
     * Check if a {@code List} is empty or {@code null}.
     *
     * @param list {@code List},may be {@code null}.
     * @return true if the {@code List} is empty or {@code null}.
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || (0 == list.size());
    }

    /**
     * Check if a {@code List} is not empty and not {@code null}.
     *
     * @param list {@code List},may be {@code null}.
     * @return true if the {@code List} is not empty and not {@code null}.
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    /**
     * Check if an array is empty or null.
     *
     * @param array an array, may be null.
     * @param <T>   the component type of the array.
     * @return true if the array is empty or {@code null}.
     */
    public static <T> boolean isEmpty(final T[] array) {
        return null == array || (0 == array.length);
    }

    /**
     * Check if an array is not empty and not null.
     *
     * @param array an array, may be null.
     * @param <T>   the component type of the array.
     * @return true if the array is not empty and not {@code null}.
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

}

