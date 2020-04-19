package com.javarush.task.task15.task1527;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Парсер реквестов
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String url = reader.readLine();
        String[] words = url.replaceFirst("^.+\\?", "").split("&");
        String d = "";

        for (String s : words) {
            if (s.contains("obj")) d = s.split("=")[1];
            System.out.print(s.replaceAll("=.+", "") + " ");
        }

        System.out.println();

        if (!d.equals("")) {
            if (d.matches("(-?)\\d*.\\d+"))
                alert(Double.parseDouble(d.replaceAll("[^0-9.]+", "")));
            else alert(d);
        }
    }

    public static void alert(double value) {
        System.out.println("double: " + value);
    }

    public static void alert(String value) {
        System.out.println("String: " + value);
    }
}
