package com.dove.breed.utils;

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
}
