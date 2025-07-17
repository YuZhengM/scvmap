package com.spring.boot.util.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * 关于时间的一些公共方法
 *
 * @author Zhengmin Yu
 */
public class TimeUtil {

    /**
     * 用于显示存储的格式
     */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**
     * 用于显示 ID 的格式
     */
    private static final DateTimeFormatter DATE_FORMAT_ID = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter LOCAL_DATE = DateTimeFormatter.ISO_LOCAL_DATE;
    /**
     * yyyy-MM-dd
     */
    private static final DateTimeFormatter DATE = DateTimeFormatter.ISO_DATE;
    /**
     * HH:mm:ss.SSS
     */
    private static final DateTimeFormatter LOCAL_TIME = DateTimeFormatter.ISO_LOCAL_TIME;
    /**
     * HH:mm:ss.SSS
     */
    private static final DateTimeFormatter TIME = DateTimeFormatter.ISO_TIME;
    /**
     * yyyy-MM-ddTHH:mm:ss.SSS
     */
    private static final DateTimeFormatter LOCAL_DATE_TIME = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
    /**
     * yyyy-MM-ddTHH:mm:ss.SSS
     */
    private static final DateTimeFormatter DATE_TIME = DateTimeFormatter.ISO_DATE_TIME;
    /**
     * 2021-107
     */
    private static final DateTimeFormatter ORDINAL_DATE = DateTimeFormatter.ISO_ORDINAL_DATE;
    /**
     * 2021-W16-3
     */
    private static final DateTimeFormatter WEEK_DATE = DateTimeFormatter.ISO_WEEK_DATE;
    /**
     * JWT 中 token 中的开头词
     * <pre>yyyyMMdd</pre>
     */
    private static final DateTimeFormatter BASIC_DATE = DateTimeFormatter.BASIC_ISO_DATE;

    /**
     * 秒与毫秒的换算关系
     */
    private static final long SECONDS_TO_MILLISECONDS_NUMBER = 1000L;

    /**
     * 用于存储数据库表中的唯一标识符 ID 的一部分内容，代表时间
     *
     * @return 返回日期
     */
    public static String getDateId() {
        return LocalDateTime.now().format(DATE_FORMAT_ID);
    }

    /**
     * JWT 中 token 中的开头词-每日一更新
     *
     * @return 返回日期
     */
    public static String getBasicTime() {
        return LocalDateTime.now().format(BASIC_DATE);
    }

    /**
     * 得到当前时间
     *
     * @return 返回日期
     */
    public static String getLocalDateTime() {
        return LocalDateTime.now().format(LOCAL_DATE_TIME);
    }

    /**
     * 通过日期获取年份
     *
     * @param date 日期
     * @return 年份
     */
    public static Integer getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 通过日期获取月份
     *
     * @param date 日期
     * @return 月份
     */
    public static Integer getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 通过日期获取几号
     *
     * @param date 日期
     * @return 日
     */
    public static Integer getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 两个日期相差的年份
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 几年
     */
    public static Integer yearDifferent(Date startDate, Date endDate) {
        return getYear(endDate) - getYear(startDate);
    }

    /**
     * 日期距离现在相差的年份
     *
     * @param date 日期
     * @return 几年
     */
    public static Integer yearDifferent(Date date) {
        return getYear(new Date()) - getYear(date);
    }

    /**
     * 停止 s 秒
     *
     * @param s 秒
     */
    public static void sleep(int s) {
        try {
            Thread.sleep(s * SECONDS_TO_MILLISECONDS_NUMBER);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始计时
     *
     * @return long
     */
    public static long startTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取结束时间并得到计时结果
     * <p>单位为 s</p>
     *
     * @return 计时结果
     */
    public static int endTime(long startTime) {
        return (int) ((System.currentTimeMillis() - startTime) / SECONDS_TO_MILLISECONDS_NUMBER);
    }

}
