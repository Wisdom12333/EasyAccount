package com.shirj.svc.utils;


import java.util.List;

/**
 * <p>Utils for the arrays.</p>
 *
 * @author shirj, wisdom12333@iCloud.com.
 */
public final class ArrayUtils {

    /**
     * <p>Check if a {@code List} is empty or {@code null}.</p>
     *
     * @param list {@code List},may be {@code null}.
     * @return true if the {@code List} is empty or {@code null}.
     */
    public static boolean isEmpty(List<?> list) {
        return null == list || (0 == list.size());
    }

    /**
     * <p>Check if a {@code List} is not empty and not {@code null}.</p>
     *
     * @param list {@code List},may be {@code null}.
     * @return true if the {@code List} is not empty and not {@code null}.
     */
    public static boolean isNotEmpty(List<?> list) {
        return !isEmpty(list);
    }

    /**
     * <p>Check if an array is empty or null.</p>
     *
     * @param array an array, may be null.
     * @param <T>   the component type of the array.
     * @return true if the array is empty or {@code null}.
     */
    public static <T> boolean isEmpty(final T[] array) {
        return null == array || (0 == array.length);
    }

    /**
     * <p>Check if an array is not empty and not null.</p>
     *
     * @param array an array, may be null.
     * @param <T>   the component type of the array.
     * @return true if the array is not empty and not {@code null}.
     */
    public static <T> boolean isNotEmpty(final T[] array) {
        return !isEmpty(array);
    }

}

