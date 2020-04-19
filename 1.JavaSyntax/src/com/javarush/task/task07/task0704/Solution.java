package com.javarush.task.task07.task0704;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/* 
Переверни массив
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] n = new int[10];
        for (int i = 0; i < 10; i++) n[i] = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
        for (int i = 0; i < 10; i++) System.out.println(n[9-i]);
    }
}

