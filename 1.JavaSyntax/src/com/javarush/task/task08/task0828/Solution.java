package com.javarush.task.task08.task0828;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/* 
Номер месяца
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("JANUARY", "1");
        map.put("FEBRUARY", "2");
        map.put("MARCH", "3");
        map.put("APRIL", "4");
        map.put("MAY", "5");
        map.put("JUNE", "6");
        map.put("JULY", "7");
        map.put("AUGUST", "8");
        map.put("SEPTEMBER", "9");
        map.put("OCTOBER", "10");
        map.put("NOVEMBER", "11");
        map.put("DECEMBER", "12");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String month = reader.readLine();
        String new_month = month.substring(0,1).toUpperCase() + month.substring(1).toLowerCase();
        if (map.keySet().contains(month.toUpperCase()))
            System.out.println(new_month + " is the " + map.get(month.toUpperCase()) + " month");

    }
}
