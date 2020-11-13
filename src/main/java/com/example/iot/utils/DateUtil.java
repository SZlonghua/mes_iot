package com.example.iot.utils;


import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期处理
 *
 * @author liaotao
 * @date 2018-11-21
 */
public class DateUtil {

  /** 时间格式(yyyy-MM-dd) */
  public final static String DATE_PATTERN = "yyyy-MM-dd";
  /** 时间格式(yyyy-MM-dd HH:mm:ss) */
  public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
  /** 时间格式(HH:mm:ss) */
  public final static String TIME_PATTERN = "HH:mm:ss";
  public final static String TIME_ZERO = "00:00:00";



  /**
     * 日期格式化 日期格式为：yyyy-MM-dd HH:mm:ss
     * @param date  日期
     * @return  返回yyyy-MM-dd HH:mm:ss格式日期
     */
  public static String format(Date date) {
    return format(date, DATE_TIME_PATTERN);
  }


  /**
   * 日期格式化 日期格式为：yyyy-MM-dd
   * @param date  日期
   * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
   * @return  返回yyyy-MM-dd格式日期
   */
  public static String format(Date date, String pattern) {
    if(date != null){
      SimpleDateFormat df = new SimpleDateFormat(pattern);
      return df.format(date);
    }
    return null;
  }

}
