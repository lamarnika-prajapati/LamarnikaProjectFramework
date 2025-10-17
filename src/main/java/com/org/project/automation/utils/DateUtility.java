package com.org.project.automation.utils;

import org.apache.poi.openxml4j.opc.PackageRelationship;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Rajat Prajapati
 */
public class DateUtility {
    /**
     * Method returns today's date value
     *
     * @param format
     * @return today's date
     */
    public static String getTodaysDate(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(new Date());
    }

    /**
     * Method to calculate relative date with given difference by days
     *
     * @param format
     * @param dateValue
     * @param noOfDays
     * @return calculate date from after/before no of days
     */

    public static String getRelativeDate(String format, String dateValue, int noOfDays) {
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(format);
        try {
            calendar.setTime(dateFormat.parse(dateValue));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.add(Calendar.DAY_OF_MONTH, noOfDays);
        return dateFormat.format(calendar.getTime());
    }
}
