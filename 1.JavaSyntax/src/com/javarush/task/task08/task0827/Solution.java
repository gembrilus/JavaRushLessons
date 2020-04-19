package com.javarush.task.task08.task0827;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* 
Работа с датой
*/

public class Solution {
    public static void main(String[] args) throws ParseException {
        System.out.println(isDateOdd("MAY 1 2013"));
        System.out.println(isDateOdd("MAY 13 2019"));
        System.out.println(isDateOdd("APRIL 11 1998"));
        System.out.println(isDateOdd("OCTOBER 17 2017"));
    }

    public static boolean isDateOdd(String date) throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Date endDate = dateFormat.parse(date);
        Date startDate = new Date();
        startDate.setDate(1);
        startDate.setMonth(0);
        startDate.setYear(endDate.getYear());

        Long diffDate = (endDate.getTime()-startDate.getTime())/86400000;
        System.out.println(diffDate);
        return diffDate % 2 != 0;
    }
}
