package com.javarush.task.task04.task0429;

/* 
Положительные и отрицательные числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int positiv = 0, negativ = 0;
        for(int i = 0, a = 0; i < 3; i++)
        {
            a = Integer.parseInt(bufferedReader.readLine());
            if(a > 0) positiv++;
            else if (a < 0) negativ++;

        }
        System.out.println("количество отрицательных чисел: " + negativ);
        System.out.println("количество положительных чисел: " + positiv);

    }
}
