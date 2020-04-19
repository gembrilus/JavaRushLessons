package com.javarush.task.task03.task0319;

/* 
Предсказание на будущее
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String name = bf.readLine();
        int number1 = Integer.parseInt(bf.readLine());
        int number2 = Integer.parseInt(bf.readLine());
        System.out.println(name + " получает " + number1 + " через " + number2 + " лет.");


    }
}
