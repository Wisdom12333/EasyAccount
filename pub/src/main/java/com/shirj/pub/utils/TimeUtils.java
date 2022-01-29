package com.shirj.pub.utils;

import org.apache.commons.lang3.StringUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Utils for Time.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public final class TimeUtils {

    public static final String LAST_SECOND = " 23:59:59";
    public static final String FIRST_SECOND = "00:00:00";
    public static final String DATE_FMT_0 = "yyyyMMdd";
    public static final String DATE_FMT_1 = "yyyy/MM/dd";
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";
    public static final String DATE_FMT_3 = "yyyy-MM-dd";
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";
    public static final String DATE_FMT_6 = "yyyy-MM";
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";
    public static final String DATE_FMT_8 = "HH:mm:ss";
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";
    public static final String TIME_STANDARD = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_STANDARD = "yyyy-MM-dd";

    public static final Integer TIME_LENGTH = 10;

    public static LocalDateTime string2Time(String time, String format) {
        if (time.length() == TIME_LENGTH && StringUtils.equals(format, TimeUtils.TIME_STANDARD)) {
            time += " " + TimeUtils.FIRST_SECOND;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(time, formatter);
    }

    /**
     * Now local date time.
     *
     * @return the local date time now
     */
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    /**
     * change a {@code Date} to a {@code LocalDateTime}.
     *
     * @param date the {@code Date}
     * @return a {@code LocalDateTime}
     */
    public static LocalDateTime date2LocalDateTime(final Date date) {
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }


}
