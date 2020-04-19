package com.javarush.task.task07.task0706;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/* 
Улицы и дома
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        int[] arr = new int[15];
        for (int i = 0; i < 15; i++) arr[i] = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int nsum = 0, psum = 0;

        for(int i = 0, k = 1; k < 15; i+=2, k+=2){
            psum += arr[i];
            nsum += arr[k];
        }

        if(nsum > psum) System.out.println("В домах с нечетными номерами проживает больше жителей.");
        else System.out.println("В домах с четными номерами проживает больше жителей.");
    }
}
