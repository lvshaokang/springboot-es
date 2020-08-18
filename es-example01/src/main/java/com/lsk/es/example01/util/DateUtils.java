package com.lsk.es.example01.util;

import lombok.val;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author lsk
 * @class_name DateUtils
 * @date 2020-06-28
 */
public class DateUtils {

    public static final String DATE_FORMAT_01 = "yyyy-MM-dd HH:mm:ss";

    public static String dateToString(Date date,String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(date);
    }

    public static String dateToString(Date date) {
        return dateToString(date, DATE_FORMAT_01);
    }

}
