package com.exodia.common.util;

import org.apache.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by manlm1 on 9/22/2015.
 */
public class DateTimeUtil {

    private static final Logger LOG = Logger.getLogger(DateTimeUtil.class);

    /**
     * Get current DateTime in millis
     *
     * @return
     */
    public static long getCurUTCInMilliseconds() {
        LOG.info("[getCurUTCInMilliseconds] Start");
        LOG.info("[getCurUTCInMilliseconds] End");
        return System.currentTimeMillis();
    }

    /**
     * Convert millis to DateTime
     *
     * @param milliSeconds
     * @param dateFormat
     * @return
     */
    public static String getDate(long milliSeconds, String dateFormat) {

        LOG.info(new StringBuilder("[getDate] Start: milliSeconds = ").append(milliSeconds)
                .append(", dateFormat = ").append(dateFormat));

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);

        LOG.info("[getDate] End");
        return formatter.format(calendar.getTime());
    }
}
