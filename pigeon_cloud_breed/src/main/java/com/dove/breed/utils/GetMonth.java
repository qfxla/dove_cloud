package com.dove.breed.utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: xbx
 */
public class GetMonth {
    public static String getDifferenceNowToMonth(Integer difference){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH,difference);
        Date m = c.getTime();
        String mon = format.format(m);

        return mon;
    }

    public static String getDateToString(Date date){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        Date date1 = c.getTime();
        String dateString = format.format(date1);
        return dateString;
    }

    //获取前几天后几天的日期
    public static Map<String, Integer> getDay(int n){
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.DATE,n);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH) + 1;
        int year = cal.get(Calendar.YEAR);
        Map<String, Integer> map = new HashMap<>();
        map.put("day",day);
        map.put("month",month);
        map.put("year",year);
        return map;
    }

}
