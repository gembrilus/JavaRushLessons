package com.javarush.task.task05.task0507;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Среднее арифметическое
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int i = 0, mean = 0;
        while (true) {
            int number = Integer.parseInt(bf.readLine());
            if ( number == -1) break;

            mean +=number;
            i++;
        }
        System.out.println((double) mean/i);
    }
}

