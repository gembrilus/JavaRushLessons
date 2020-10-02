package com.javarush.task.task40.task4007;

/*
Работа с датами
*/

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Solution {
    public static void main(String[] args) {
        printDate("21.4.2014 15:56:45");
        System.out.println();
        printDate("21.4.2014");
        System.out.println();
        printDate("17:33:40");
    }

    public static void printDate(String date) {
        String dateTimePattern = "dd.MM.yyyy HH:mm:ss";
        String datePattern = "dd.MM.yyyy";
        String timePattern = "HH:mm:ss";

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf;
        try {
            if (date.matches("\\d{1,2}.\\d{1,2}.\\d{4} \\d{1,2}:\\d{1,2}:\\d{1,2}")) {
                sdf = new SimpleDateFormat(dateTimePattern);
                calendar.setTime(sdf.parse(date));
                printDateTime(calendar);
            } else if (date.matches("\\d{1,2}.\\d{1,2}.\\d{4}")){
                sdf = new SimpleDateFormat(datePattern);
                calendar.setTime(sdf.parse(date));
                printDate(calendar);
            } else {
                sdf = new SimpleDateFormat(timePattern);
                calendar.setTime(sdf.parse(date));
                printTime(calendar);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            calendar.clear();
        }
    }

    private static void printDateTime(Calendar calendar){
        System.out.format("День: %d%n" +
                "День недели: %d%n" +
                "День месяца: %d%n" +
                "День года: %d%n" +
                "Неделя месяца: %d%n" +
                "Неделя года: %d%n" +
                "Месяц: %d%n" +
                "Год: %d%n" +
                "AM или PM: %s%n" +
                "Часы: %d%n" +
                "Часы дня: %d%n" +
                "Минуты: %d%n" +
                "Секунды: %d%n",
                calendar.get(Calendar.DAY_OF_MONTH),
                (calendar.get(Calendar.DAY_OF_WEEK) - 1 == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.DAY_OF_YEAR),
                calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.WEEK_OF_YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.YEAR),
                (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }

    private static void printDate(Calendar calendar){
        System.out.format("День: %d%n" +
                        "День недели: %d%n" +
                        "День месяца: %d%n" +
                        "День года: %d%n" +
                        "Неделя месяца: %d%n" +
                        "Неделя года: %d%n" +
                        "Месяц: %d%n" +
                        "Год: %d%n",
                calendar.get(Calendar.DAY_OF_MONTH),
                ((calendar.get(Calendar.DAY_OF_WEEK) - 1) == 0 ? 7 : calendar.get(Calendar.DAY_OF_WEEK) - 1),
                calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.DAY_OF_YEAR),
                calendar.get(Calendar.WEEK_OF_MONTH),
                calendar.get(Calendar.WEEK_OF_YEAR),
                calendar.get(Calendar.MONTH)+1,
                calendar.get(Calendar.YEAR)
        );
    }

    private static void printTime(Calendar calendar){
        System.out.format("AM или PM: %s%n" +
                        "Часы: %d%n" +
                        "Часы дня: %d%n" +
                        "Минуты: %d%n" +
                        "Секунды: %d%n",
                (calendar.get(Calendar.AM_PM) == Calendar.AM ? "AM" : "PM"),
                calendar.get(Calendar.HOUR),
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                calendar.get(Calendar.SECOND)
        );
    }

}
