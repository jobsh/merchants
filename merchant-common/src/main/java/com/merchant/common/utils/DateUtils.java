package com.merchant.common.utils;

import java.lang.management.ManagementFactory;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 * 时间工具类
 * 
 * @author hanke
 */
public class DateUtils extends org.apache.commons.lang3.time.DateUtils
{
    public static String YYYY = "yyyy";

    public static String YYYY_MM = "yyyy-MM";

    public static String YYYY_MM_DD = "yyyy-MM-dd";

    public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    
    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM", 
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM"};

    /**
     * 获取当前Date型日期
     * 
     * @return Date() 当前日期
     */
    public static Date getNowDate()
    {
        return new Date();
    }

    /**
     * 获取当前日期, 默认格式为yyyy-MM-dd
     * 
     * @return String
     */
    public static String getDate()
    {
        return dateTimeNow(YYYY_MM_DD);
    }

    public static final String getTime()
    {
        return dateTimeNow(YYYY_MM_DD_HH_MM_SS);
    }

    public static final String dateTimeNow()
    {
        return dateTimeNow(YYYYMMDDHHMMSS);
    }

    public static final String dateTimeNow(final String format)
    {
        return parseDateToStr(format, new Date());
    }

    public static final String dateTime(final Date date)
    {
        return parseDateToStr(YYYY_MM_DD, date);
    }

    public static final String parseDateToStr(final String format, final Date date)
    {
        return new SimpleDateFormat(format).format(date);
    }

    public static final Date dateTime(final String format, final String ts)
    {
        try
        {
            return new SimpleDateFormat(format).parse(ts);
        }
        catch (ParseException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * 日期路径 即年/月/日 如2018/08/08
     */
    public static final String datePath()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyy/MM/dd");
    }

    /**
     * 日期路径 即年/月/日 如20180808
     */
    public static final String dateTime()
    {
        Date now = new Date();
        return DateFormatUtils.format(now, "yyyyMMdd");
    }

    /**
     * 日期型字符串转化为日期 格式
     */
    public static Date parseDate(Object str)
    {
        if (str == null)
        {
            return null;
        }
        try
        {
            return parseDate(str.toString(), parsePatterns);
        }
        catch (ParseException e)
        {
            return null;
        }
    }
    
    /**
     * 获取服务器启动时间
     */
    public static Date getServerStartDate()
    {
        long time = ManagementFactory.getRuntimeMXBean().getStartTime();
        return new Date(time);
    }

    /**
     * 计算两个时间差
     */
    public static String getDatePoor(Date endDate, Date nowDate)
    {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }

    public static boolean isSameDay(final Date date1, final Date date2) {
        if (date1 == null && date2 == null) {
            return true;
        }
        if ((date1 == null && date2 != null) || date1 != null && date2 == null ) {
            return false;
        }
        final Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        final Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        return isSameDay(cal1, cal2);
    }

    public static boolean isSameDay(final Calendar cal1, final Calendar cal2) {
        if (cal1 == null && cal2 == null) {
            return true;
        }
        if ((cal1 == null && cal2 != null) || cal1 == null && cal2 != null ) {
            return false;
        }
        return cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) &&
                cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR);
    }

    public static Boolean isInTimeLine(Date date, Date start, Date end) {
        Long time = date.getTime();
        Long startTime = start.getTime();
        Long endTime = end.getTime();
        if (time > startTime && time < endTime) {
            return true;
        }
        return false;
    }

    /**
     * 日期格式化
     */
    public static String format(Calendar c, String pattern) {
        Calendar calendar = null;
        if (c != null) {
            calendar = c;
        } else {
            calendar = Calendar.getInstance();
        }
        if (pattern == null || pattern.equals("")) {
            pattern = YYYY_MM_DD_HH_MM_SS;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(calendar.getTime());
    }

    /**
     * 获得某月第一天
     * @param month 第几个月
     * @return    yyyy-MM-dd
     */
    public static String getMonthFirstDay(int month) {
        Calendar strDate = Calendar.getInstance();
        int day = strDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = strDate.get(Calendar.MONTH);
        strDate.add(Calendar.DATE,-(day-1));  // 本月第一天
        strDate.add(Calendar.MONTH,-(currentMonth-month+1));  //
        return format(strDate,"yyyy-MM-dd");
    }

    /**
     * 获得某月最后一天
     * @param month 第几个月
     * @return    yyyy-MM-dd
     */
    public static String getMonthLastDay(int month) {
        Calendar strDate = Calendar.getInstance();
        int day = strDate.get(Calendar.DAY_OF_MONTH);
        int currentMonth = strDate.get(Calendar.MONTH);
        strDate.add(Calendar.DATE,-day);
        strDate.add(Calendar.MONTH,-currentMonth+month);
        return format(strDate,"yyyy-MM-dd");
    }

    /**
     * 当前季度的开始时间
     *
     * @return
     */
    public static String getCurrentQuarterStartTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String now = null;
        try {
            if (currentMonth <= 3)
                c.set(Calendar.MONTH, 1);
            else if (currentMonth <= 6)
                c.set(Calendar.MONTH, 3);
            else if (currentMonth <= 9)
                c.set(Calendar.MONTH, 4);
            else if (currentMonth <= 12)
                c.set(Calendar.MONTH, 9);
            c.set(Calendar.DATE, 1);
            now = format(c, YYYY_MM_DD) + " 00:00:00";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static String getCurrentQuarterEndTime() {
        Calendar c = Calendar.getInstance();
        int currentMonth = c.get(Calendar.MONTH) + 1;
        String now = null;
        try {
            if (currentMonth <= 3) {
                c.set(Calendar.MONTH, 2);
                c.set(Calendar.DATE, 31);
            } else if (currentMonth <= 6) {
                c.set(Calendar.MONTH, 5);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 9) {
                c.set(Calendar.MONTH,8);
                c.set(Calendar.DATE, 30);
            } else if (currentMonth <= 12) {
                c.set(Calendar.MONTH, 11);
                c.set(Calendar.DATE, 31);
            }
            now = format(c, YYYY_MM_DD) + " 23:59:59";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }

    /**
     * 获取本年的第一天
     * @return String
     * **/
    public static String getCurrentYearStart(){
        return new SimpleDateFormat("yyyy").format(new Date())+"-01-01";
    }

    /**
     * 获取本年的最后一天
     * @return String
     * **/
    public static String getCurrentYearEnd(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.MONTH,calendar.getActualMaximum(Calendar.MONTH));
        calendar.set(Calendar.DAY_OF_MONTH,calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date currYearLast = calendar.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(currYearLast)+" 23:59:59";
    }
//    public static void main(String[] args) {
//        System.out.println(getCurrentQuarterStartTime());
//        System.out.println(getCurrentQuarterEndTime());
//    }
}
