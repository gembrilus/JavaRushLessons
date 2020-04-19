package com.javarush.task.task04.task0427;

/* 
Описываем числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bufferedReader.readLine());
        if(a >= 1 && a <= 999) {
            if (a % 2 == 0 && a/10 == 0) System.out.println("четное однозначное число");
            else if (a % 2 != 0 && a/10 == 0) System.out.println("нечетное однозначное число");
            else if (a % 2 == 0 && a/100 == 0) System.out.println("четное двузначное число");
            else if (a % 2 != 0 && a/100 == 0) System.out.println("нечетное двузначное число");
            else if (a % 2 == 0) System.out.println("четное трехзначное число");
            else System.out.println("нечетное трехзначное число");
        }
    }
}
