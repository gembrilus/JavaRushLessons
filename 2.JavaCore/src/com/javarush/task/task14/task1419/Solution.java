package com.javarush.task.task14.task1419;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/* 
Нашествие исключений
*/

public class Solution {
    public static List<Exception> exceptions = new ArrayList<Exception>();

    public static void main(String[] args) {
        initExceptions();

        for (Exception exception : exceptions) {
            System.out.println(exception);
        }
    }

    private static void initExceptions() {   //the first exception
        try {
            float i = 1 / 0;

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            float i = 1;
            Object o = i;
            i = (Integer) o;

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int[] i = new int[0];
            int b = i[1];

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            new ArrayList<>().get(0);

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int i = Integer.getInteger(null);

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            Object o = new Object();
            System.out.write(Integer.parseInt(o.toString()));

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            new FileReader("");

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(1);
            list.add(1);
            list.add(1);
            list.add(1);
            list.add(1);
            for (int i: list) list.remove(i);

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            int x = 0;
            x--;
            int[] i = new int[x];

        } catch (Exception e) {
            exceptions.add(e);
        }

        try {
            "".charAt(1);

        } catch (Exception e) {
            exceptions.add(e);
        }

    }
}
