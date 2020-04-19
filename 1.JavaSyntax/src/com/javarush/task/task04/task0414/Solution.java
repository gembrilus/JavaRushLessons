package com.javarush.task.task04.task0414;

/* 
Количество дней в году
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int year = Integer.parseInt(bf.readLine());
        if(year%400 == 0) System.out.println("количество дней в году: " + 366);
        else if(year%100 == 0) System.out.println("количество дней в году: " + 365);
        else if(year%4 == 0) System.out.println("количество дней в году: " + 366);
        else System.out.println("количество дней в году: " + 365);


    }
}