package com.javarush.task.task08.task0816;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/* 
Добрая Зинаида и летние каникулы
*/

public class Solution {
    public static Map<String, Date> createMap() throws ParseException {
        DateFormat dateFormat = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        Map<String, Date> map = new HashMap<>();
        map.put("Сталлоне", dateFormat.parse("APRIL 15 1980"));
        map.put("Ковалов", dateFormat.parse("FEBRUARY 11 1991"));
        map.put("Коноваленко", dateFormat.parse("SEPTEMBER 25 1976"));
        map.put("Ковтуненко", dateFormat.parse("MAY 11 1990"));
        map.put("Дитренко", dateFormat.parse("JUNE 22 1983"));
        map.put("Путин", dateFormat.parse("AUGUST 23 1986"));
        map.put("Коновалов", dateFormat.parse("AUGUST 8 1979"));
        map.put("Кононенко", dateFormat.parse("OCTOBER 7 1978"));
        map.put("Дмитренко", dateFormat.parse("JANUARY 16 1984"));
        map.put("Гаврыш", dateFormat.parse("DECEMBER 17 1989"));
        return map;
    }

    public static void removeAllSummerPeople(Map<String, Date> map) {
        Iterator<Map.Entry<String, Date>> it = map.entrySet().iterator();
        while(it.hasNext()){
            int month = it.next().getValue().getMonth();
            if(month > 4 && month < 8) it.remove();
        }

    }

    public static void main(String[] args) {

    }
}
