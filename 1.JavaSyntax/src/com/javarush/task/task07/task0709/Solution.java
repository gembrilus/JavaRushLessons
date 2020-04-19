package com.javarush.task.task07.task0709;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Выражаемся покороче
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> strings = new ArrayList<>();
        for(int i =0; i<5;i++) strings.add(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int min = strings.get(0).length();
        for (int i = 1; i<strings.size(); i++){
            int temp = strings.get(i).length();
            if(temp < min) min = temp;
        }

        for(String s: strings)
            if(s.length() == min) System.out.println(s);
    }
}

