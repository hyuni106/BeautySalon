package com.thejoeunit.www.beautysalon.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by the on 2017-07-27.
 */

public class DateTimeUtil {
    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일");
    static SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");
    static SimpleDateFormat timeFormat = new SimpleDateFormat("a hh:mm");
    static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd a hh:mm");

    public static String getDateString(Calendar cal) {

        String str = dateFormat.format(cal.getTime());
        return str;
    }

    public static String getTimeString(Calendar cal) {
        String str = timeFormat.format(cal.getTime());
        return str;
    }

    public static String getDateTimeString(Calendar cal) {
        String str = dateTimeFormat.format(cal.getTime());
        return str;
    }

    public static String getDateString2(Calendar cal) {
        String str = dateFormat2.format(cal.getTime());
        return str;
    }
}
