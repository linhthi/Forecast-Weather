package com.app.forecastweather.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {
    public static String getImage(String icon) {
        return String.format("http://openweathermap.org/img/w/%s.png", icon);
    }

    public static int getHour(long unix_seconds) {
        int hour = (int) (unix_seconds / 3600 + 7) % 24;
        return hour;
    }

    public static String getDay(long unixSeconds) {
        Date date = new java.util.Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss z");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+7"));
        String formattedDate = sdf.format(date);
        return String.valueOf(formattedDate.toCharArray(), 0, 10)  ;
    }
}
