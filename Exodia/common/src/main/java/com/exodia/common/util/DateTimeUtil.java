package com.exodia.common.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by manlm1 on 9/22/2015.
 */
public class DateTimeUtil {

    public static String getDate(long milliSeconds, String dateFormat) {

        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
