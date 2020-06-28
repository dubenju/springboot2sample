package com.example.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;

public class DateUtil {
    private static final DateTimeFormatter DATE_TIME_FMT = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    public static LocalDateTime convertLocalDateTime(String date, String time) {
        if (StringUtils.isBlank(date) || StringUtils.isBlank(time)) {
            return null;
        }
        return LocalDateTime.parse(convertSlashYYYYMMDD(date) + " " + convertColonHHMMSS(time), DATE_TIME_FMT);
    }
    public static String convertSlashYYYYMMDD(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        return date.substring(0, 4) + "/" + date.substring(4, 6) + "/" + date.substring(6, 8);
    }
    public static String convertColonHHMMSS(String time) {
        if (StringUtils.isBlank(time)) {
            return null;
        }
        return time.substring(0, 2) + ":" + time.substring(2, 4) + ":" + time.substring(4, 6);
    }
}
