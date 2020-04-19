package com.javarush.task.task07.task0703;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Общение одиноких массивов
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        String[] str = new String[10];
        int[] index = new int[10];

        for (int i = 0; i < 10; i++){
            str[i] = new BufferedReader(new InputStreamReader(System.in)).readLine();
            index[i] = str[i].length();
        }

        for (int i: index) System.out.println(i);
    }
}
