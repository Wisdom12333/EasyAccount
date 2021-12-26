package com.shirj.pub.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * Utils for Time.
 *
 * @author shirj, wisdom12333@iCloud.com
 */
public final class TimeUtils {


    /**
     * Now local date time.
     *
     * @return the local date time now
     */
    public static LocalDateTime now(){
        return LocalDateTime.now();
    }

    /**
     * change a {@code Date} to a {@code LocalDateTime}.
     *
     * @param date the {@code Date}
     * @return a {@code LocalDateTime}
     */
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        return LocalDateTime.ofInstant(instant, zoneId);
    }


}
