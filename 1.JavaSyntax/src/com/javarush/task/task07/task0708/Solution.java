package com.javarush.task.task07.task0708;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самая длинная строка
*/

public class Solution {
    private static ArrayList<String> strings;

    public static void main(String[] args) throws Exception {
        strings = new ArrayList<>();
        for(int i =0; i<5;i++) strings.add(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int max = strings.get(0).length();
        for (int i = 1; i<strings.size(); i++){
            int temp = strings.get(i).length();
            if(temp > max) max = temp;
        }

        for(String s: strings)
            if(s.length() == max) System.out.println(s);
    }
}
