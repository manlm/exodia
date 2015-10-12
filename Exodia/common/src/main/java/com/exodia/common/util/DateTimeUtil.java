package com.exodia.common.util;

import org.apache.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    /**
     * Get current year
     *
     * @return
     */
    public static int getCurrentYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /***
     * Parse Date String to Millis
     *
     * @param parseDate
     * @param format
     * @return
     */
    public static long dateToMillis(String parseDate, String format) {

        LOG.info(new StringBuilder("[dateToMillis] Start: parseDate = ").append(parseDate)
                .append(", format = ").append(format));

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
            date = sdf.parse(parseDate);

            LOG.info("[dateToMillis] End");
            return date.getTime();
        } catch (ParseException e) {
            LOG.error(new StringBuilder("[dateToMillis] ParseException: ").append(e.getMessage()));
            return -1;
        }
    }

    /**
     * Get current month
     *
     * @return
     */
    public static int getCurrentMonth() {
        Calendar now = Calendar.getInstance();
        return (now.get(Calendar.MONTH) + 1);
    }
}
