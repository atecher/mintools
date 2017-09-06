package com.atecher.mintools.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 描述：日期工具类
 * @作者    mark.han
 * @邮箱    hongwei.han@qq.com
 * @日期    2014-7-28
 * @版本    v1.0
 */
public class DateCommonUtils {
	 public static Date date = null;
	    /**
	     * 英文简写（默认）如：2010-12-01
	     */
	    public static final String FORMAT_SHORT = "yyyy-MM-dd";
	    /**
	     * 英文全称  如：2010-12-01 23:15:06
	     */
	    public static final String FORMAT_LONG = "yyyy-MM-dd HH:mm:ss";
	    /**
	     * 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
	     */
	    public static final String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
	    /**
	     * 中文简写  如：2010年12月01日
	     */
	    public static final String FORMAT_SHORT_CN = "yyyy年MM月dd日";
	    
	    public static final String FORMAT_MID_CN_MIN = "yyyy年MM月dd日 HH时mm分";
	    
	    public static final String FORMAT_MID_CN_HOUR = "yyyy年MM月dd日 HH时mm分";
	    
	    /**
	     * 中文全称  如：2010年12月01日  23时15分06秒
	     */
	    public static final String FORMAT_LONG_CN = "yyyy年MM月dd日  HH时mm分ss秒";
	    
	    /**
	     * 精确到毫秒的完整中文时间
	     */
	    public static final String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";

	    /**
	     * 获得默认的 date pattern
	     */
	    public static String getDatePattern() {
	        return FORMAT_LONG;
	    }

	    /**
	     * 根据预设格式返回当前日期
	     * @return
	     */
	    public static String getNow() {
	        return format(new Date());
	    }
	    
	    /**
	     * 根据用户格式返回当前日期
	     * @param format
	     * @return
	     */
	    public static String getNow(String format) {
	        return format(new Date(), format);
	    }

	    
	    /**
	     * 使用预设格式格式化日期
	     * @param date
	     * @return
	     */
	    public static String format(Date date) {
	        return format(date, getDatePattern());
	    }

	    /**
	     * 使用用户格式格式化日期
	     * @param date 日期
	     * @param pattern 日期格式
	     * @return
	     */
	    public static String format(Date date, String pattern) {
	        String returnValue= "";
	        if (date != null) {
	            SimpleDateFormat df = new SimpleDateFormat(pattern);
	            returnValue = df.format(date);
	        }
	        return (returnValue);
	    }

	    /**
	     * 使用预设格式提取字符串日期
	     * @param strDate 日期字符串
	     * @return
	     */
	    public static Date parse(String strDate) {
	        return parse(strDate, getDatePattern());
	    }

	    /**
	     * 使用用户格式提取字符串日期
	     * @param strDate 日期字符串
	     * @param pattern 日期格式
	     * @return
	     */
	    public static Date parse(String strDate, String pattern) {
	        SimpleDateFormat df = new SimpleDateFormat(pattern);
	        try {
	            return df.parse(strDate);
	        } catch (ParseException e) {
	            e.printStackTrace();
	            return null;
	        }
	    }

	    /**
	     * 在日期上增加数个整月
	     * @param date 日期
	     * @param n 要增加的月数
	     * @return
	     */
	    public static Date addMonth(Date date, int n) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.MONTH, n);
	        return cal.getTime();
	    }

	    /**
	     * 在日期上增加天数
	     * @param date 日期
	     * @param n 要增加的天数
	     * @return
	     */
	    public static Date addDay(Date date, int n) {
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        cal.add(Calendar.DATE, n);
	        return cal.getTime();
	    }
	    
	    /**
	     * 获取距现在某一小时的时刻
	     * @param format 格式化时间的格式
	     * @param h    距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
	     * @return
	     */
	    public static String getpreHour(String format, int h){
	        SimpleDateFormat sdf = new SimpleDateFormat(format);
	        Date date = new Date();
			long time= Long.valueOf(h*60*60*1000).longValue()+date.getTime();
	        date.setTime(time);
	        return sdf.format(date);
	    }
	    /**
	     * 获取时间戳
	     */
	    public static String getTimeString() {
	        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
	        Calendar calendar = Calendar.getInstance();
	        return df.format(calendar.getTime());
	    }

	    /**
	     * 获取日期年份
	     * @param date 日期
	     * @return
	     */
	    public static String getYear(Date date) {
	        return format(date).substring(0, 4);
	    }
	    /**
	     * 功能描述：返回月
	     * @param date Date 日期
	     * @return 返回月份
	     */
	    public static int getMonth(Date date) {
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.MONTH) + 1;
	    }

	    /**
	     * 功能描述：返回日
	     * @param date Date 日期
	     * @return 返回日份
	     */
	    public static int getDay(Date date) {
	    	Calendar calendar  = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.DAY_OF_MONTH);
	    }

	    /**
	     * 功能描述：返回小
	     * @param date 日期
	     * @return 返回小时
	     */
	    public static int getHour(Date date) {
	    	Calendar calendar  = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.HOUR_OF_DAY);
	    }

	    /**
	     * 功能描述：返回分
	     * @param date 日期
	     * @return 返回分钟
	     */
	    public static int getMinute(Date date) {
	    	Calendar calendar  = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.MINUTE);
	    }

	    /**
	     * 返回秒钟
	     * @param date Date 日期
	     * @return 返回秒钟
	     */
	    public static int getSecond(Date date) {
	    	Calendar calendar = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.get(Calendar.SECOND);
	    }

	    /**
	     * 功能描述：返回毫
	     * @param date 日期
	     * @return 返回毫
	     */
	    public static long getMillis(Date date) {
	    	Calendar calendar  = Calendar.getInstance();
	        calendar.setTime(date);
	        return calendar.getTimeInMillis();
	    }
	    /**
	     * 按默认格式的字符串距离今天的天数
	     * @param date 日期字符串
	     * @return
	     */
	    public static int countDays (String date) {
	        long t = Calendar.getInstance().getTime().getTime();
	        Calendar c = Calendar.getInstance();
	        c.setTime(parse(date));
	        long t1 = c.getTime().getTime();
	        return (int)(t/1000 - t1/1000)/3600/24;
	    }
	    
	    /**
	     * 按用户格式字符串距离今天的天数
	     * @param date 日期字符串
	     * @param format 日期格式
	     * @return
	     */
	    public static int countDays (String date, String format) {
	        long t = Calendar.getInstance().getTime().getTime();
	        Calendar c = Calendar.getInstance();
	        c.setTime(parse(date, format));
	        long t1 = c.getTime().getTime();
	        return (int)(t/1000 - t1/1000)/3600/24;
	    }

}
