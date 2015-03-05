
package com.grizzly.functions;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created on 14-04-14.
 */
public class DateFunctions {


    /**
     * Converts a java.util.Date object to an int timestamp valid value.
     * @param date the date to be converted.
     * @return an int timestamp representation of the int value.
     */
    public static int getTimestamp(Date date) {
        return (int)(date.getTime()/1000L);
    }

    /**
     * Converts an int timestamp into a date
     *
     * @param time an int timestamp
     * @return a date object representation of the given timestamp.
     */
    public static Date getDate(int time) {
        Date d = new Date();
        long t = time;
        d.setTime(t * 1000L);
        return d;
    }

    /**
     * Converts an int timestamp into a date
     *
     * @param time an int timestamp
     * @return a date object representation of the given timestamp.
     */
    public static Date getDate(Long time) {
        Date d = new Date();
        d.setTime(time);
        return d;
    }

    /**
     * Converts the date to a readable string.
     *
     * @param date the date to be converted.
     * @return a date string in yyyy/MM/dd format.
     */
    public static String getFormattedDate(Date date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        return sd.format(date);
    }

    /**
     * Converts a int timestamp to a readable string.
     *
     * @param time the int timestamp to be converted.
     * @return a date string in yyyy/MM/dd format.
     */
    public static String getFormattedDate(int time) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd");
        return sd.format(DateFunctions.getDate(time));
    }

    /**
     * Converts a int timestamp to a readable string.
     *
     * @param time the int timestamp to be converted.
     * @return a date string in yyyy/MM/dd format.
     */
    public static String getFormattedDate(long time) {
        SimpleDateFormat sd = new SimpleDateFormat("YYYY/MM/dd");
        Date theDate = new Date();
        theDate.setTime(time);
        return sd.format(theDate);
    }

    /**
     * Converts a int timestamp to a readable string.
     *
     * @param time the int timestamp to be converted.
     * @return a date string in YYYY/MM/dd format.
     */
    public static String getFormattedDateAndTime(int time) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ssz");
        return sd.format(DateFunctions.getDate(time));
    }

    /**
     * Converts a int timestamp to a readable string.
     *
     * @param time the int timestamp to be converted.
     * @return a date string in YYYY/MM/dd format.
     */
    public static String getFormattedDateAndTime(long time) {
        SimpleDateFormat sd = new SimpleDateFormat("YYYY/MM/dd HH:mm:ssz");
        Date theDate = new Date();
        theDate.setTime(time);
        return sd.format(theDate);
    }

    /**
     * Converts a java.util.Date object to an integer representation (julian date)
     * @param pDate the date to be converted
     * @return an int representing the given date in julian format
     */
    public static Integer toJulianDate(Date pDate) {
        if (pDate == null) {
            return null;
        }
        Calendar lCal = Calendar.getInstance();
        lCal.setTime(pDate);
        int lYear = lCal.get(Calendar.YEAR);
        int lMonth = lCal.get(Calendar.MONTH) + 1;
        int lDay = lCal.get(Calendar.DATE);
        int a = (14 - lMonth) / 12;
        int y = lYear + 4800 - a;
        int m = lMonth + 12 * a - 3;
        return lDay + (153 * m + 2) / 5 + 365 * y + y / 4 - y / 100 + y / 400 - 32045;
    }


}
