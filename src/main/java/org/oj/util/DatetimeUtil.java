package org.oj.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期处理工具，java8
 *
 * @author DH
 * @create 2021-10-20
 * @create 2022-07-22
 */
@Slf4j
public class DatetimeUtil {

    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private static final String DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final ThreadLocal<Map<String, DateTimeFormatter>> DATE_THREAD_LOCAL = new ThreadLocal<>();

    /**
     * 当前日期格式化
     *
     * @return String
     */
    public static String formatDate() {
        return formatDate(LocalDateTime.now().toLocalDate(), DATE_FORMAT);
    }

    /**
     * 日期格式化, yyyy-MM-dd
     *
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatDate(LocalDateTime localDateTime) {
        return formatDate(localDateTime.toLocalDate(), DATE_FORMAT);
    }

    /**
     * 日期格式化, yyyy-MM-dd
     *
     * @param localDate LocalDate
     * @return String
     */
    public static String formatDate(LocalDate localDate) {
        return formatDate(localDate, DATE_FORMAT);
    }

    /**
     * 当前日期时间格式化, yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String formatDateTime() {
        return formatDatetime(LocalDateTime.now(), DATETIME_FORMAT);
    }

    /**
     * 日期时间格式化, yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime LocalDateTime
     * @return String
     */
    public static String formatDateTime(LocalDateTime localDateTime) {
        return formatDatetime(localDateTime, DATETIME_FORMAT);
    }

    /**
     * 当前日期格式化
     *
     * @param pattern 格式
     * @return String
     */
    public static String formatDate(String pattern) {
        // 校验入参
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("格式字符串不能为空");
        }
        return LocalDate.now().format(getDateFormat(pattern));
    }

    /**
     * 日期格式化
     *
     * @param localDate localDate
     * @param pattern   格式
     * @return String
     */
    public static String formatDate(LocalDate localDate, String pattern) {
        if (localDate == null) {
            throw new IllegalArgumentException("localDate不能为空");
        }
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("格式字符串不能为空");
        }
        return localDate.format(getDateFormat(pattern));
    }

    /**
     * 当前日期时间格式化
     *
     * @param pattern 格式
     * @return String
     */
    public static String formatDateTime(String pattern) {
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("格式字符串不能为空");
        }
        return LocalDateTime.now().format(getDateFormat(pattern));
    }

    /**
     * 日期时间格式化
     *
     * @param localDateTime LocalDateTime
     * @param pattern       格式
     * @return String
     */
    public static String formatDatetime(LocalDateTime localDateTime, String pattern) {
        if (localDateTime == null) {
            return null;
        }
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("格式字符串不能为空");
        }
        return localDateTime.format(getDateFormat(pattern));
    }

    /**
     * "yyyy-MM-dd"字符串转日期
     *
     * @param dateStr "yyyy-MM-dd"格式字符串
     * @return LocalDate
     */
    public static LocalDate parseDate(String dateStr) {
        return parseDate(dateStr, DATE_FORMAT);
    }

    /**
     * "yyyy-MM-dd HH:mm:ss"字符串转日期时间
     *
     * @param dateStr "yyyy-MM-dd HH:mm:ss"格式字符串
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateStr) {
        return parseDateTime(dateStr, DATETIME_FORMAT);
    }

    /**
     * 字符串转日期
     *
     * @param dateStr 日期字符串
     * @param pattern 格式
     * @return LocalDate
     */
    public static LocalDate parseDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, getDateFormat(pattern));
    }

    /**
     * 字符串转日期时间
     *
     * @param dateStr 日期时间字符串
     * @param pattern 格式
     * @return LocalDateTime
     */
    public static LocalDateTime parseDateTime(String dateStr, String pattern) {
        return LocalDateTime.parse(dateStr, getDateFormat(pattern));
    }

    /**
     * 同个线程，可以避免创建很多个DateFormat.
     *
     * @param pattern 格式
     * @return DateTimeFormatter
     */
    private static DateTimeFormatter getDateFormat(String pattern) {
        // 校验入参
        if (StringUtils.isBlank(pattern)) {
            throw new IllegalArgumentException("格式字符串不能为空");
        }

        // 获取DateFormat
        Map<String, DateTimeFormatter> dateFormatMap = DATE_THREAD_LOCAL.get();
        if (dateFormatMap != null && dateFormatMap.containsKey(pattern)) {
            return dateFormatMap.get(pattern);
        }

        synchronized (DATE_THREAD_LOCAL) {
            if (dateFormatMap == null) {
                dateFormatMap = new HashMap<>();
            }
            dateFormatMap.put(pattern, DateTimeFormatter.ofPattern(pattern));
            DATE_THREAD_LOCAL.set(dateFormatMap);
        }

        return dateFormatMap.get(pattern);
    }
}
