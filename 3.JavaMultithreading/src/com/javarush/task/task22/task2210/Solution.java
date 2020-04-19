package com.javarush.task.task22.task2210;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
StringTokenizer
*/
public class Solution {
    public static void main(String[] args) {
    }
    public static String [] getTokens(String query, String delimiter) {
        List<String> list = new ArrayList<>();
        StringTokenizer s = new StringTokenizer(query, delimiter);
        while (s.hasMoreTokens()){
            list.add(s.nextToken());
        }
        return list.toArray(new String[0]);
    }
}
