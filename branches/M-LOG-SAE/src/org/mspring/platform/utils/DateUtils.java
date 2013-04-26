/**
 * 
 */
package org.mspring.platform.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Gao Youbo
 * @since Feb 20, 2012
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils {
    public static final String YYYY = "yyyy";
    public static final String YYYY_MM = "yyyy-MM";
    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date parse(String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            return null;
        }
        dateStr = dateStr.trim();
        Date date = null;
        if (dateStr.length() == YYYY.length()) {
            date = parse(dateStr, YYYY);
        } else if (dateStr.length() == YYYY_MM.length()) {
            date = parse(dateStr, YYYY_MM);
        } else if (dateStr.length() == YYYY_MM_DD.length()) {
            date = parse(dateStr, YYYY_MM_DD);
        } else if (dateStr.length() == YYYY_MM_DD_HH_MM.length()) {
            date = parse(dateStr, YYYY_MM_DD_HH_MM);
        } else {
            date = parse(dateStr, YYYY_MM_DD_HH_MM_SS);
        }
        return date;
    }

    public static Date parse(String dateStr, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        Date date = null;
        try {
            fmt.applyPattern(pattern);
            date = fmt.parse(dateStr);
        } catch (ParseException e) {
            try {
                fmt.applyPattern("yyyy-MM-dd HH:mm");
                date = fmt.parse(dateStr);
            } catch (ParseException e1) {
                fmt.applyPattern("yyyy-MM-dd");
                try {
                    date = fmt.parse(dateStr);
                } catch (ParseException e2) {
                    e2.printStackTrace();
                }
            }
        }
        return date;
    }

    public static String format(String pattern) {
        return format(new Date(), pattern);
    }

    public static String format(Date date, String pattern) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        fmt.applyPattern(pattern);
        return fmt.format(date);
    }

    public static Long parseLong() {
        return parseLong(new Date());
    }

    public static Long parseLong(Date date) {
        return date.getTime();
    }
}
