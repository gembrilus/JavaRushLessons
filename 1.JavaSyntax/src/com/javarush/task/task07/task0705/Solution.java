package com.javarush.task.task07.task0705;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Один большой массив и два маленьких
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        int[] arr = new int[20];
        for (int i = 0; i < 20; i++) arr[i] = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int[] arr1 = new int[10];
        int[] arr2 = new int[10];

        System.arraycopy(arr,0,arr1,0,10);
        System.arraycopy(arr,10,arr2,0,10);

        for(int i: arr2) System.out.println(i);
    }
}
