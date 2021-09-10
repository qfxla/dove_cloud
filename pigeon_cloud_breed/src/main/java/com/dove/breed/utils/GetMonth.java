package com.dove.breed.utils;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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

    public static String getDateToString(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        Date date = c.getTime();
        String dateString = format.format(date);
        StringBuilder stringBuilder = new StringBuilder(dateString);
        int i = stringBuilder.indexOf("-");
        StringBuilder stringBuilder1 = stringBuilder.delete(i, i + 1);
        int i1 = stringBuilder1.indexOf("-");
        StringBuilder stringBuilder2 = stringBuilder1.delete(i1, i1 + 1);
        dateString = stringBuilder2.toString();
        return dateString;
    }
}
