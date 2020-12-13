package edu.zust.se.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static Date getDate(int year,int month,int day,int hour,int minute,int second){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month-1, day, hour, minute, second);//月份从0开始 要减一
        Date date = calendar.getTime();
        return date;
    }
}
