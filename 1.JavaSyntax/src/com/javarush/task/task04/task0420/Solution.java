package com.javarush.task.task04.task0420;

/* 
Сортировка трех чисел
*/

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[3];

        for(int i =0; i <3; i++)
            numbers[i] = Integer.parseInt(bufferedReader.readLine());

        Arrays.sort(numbers);

        System.out.println(numbers[2] + " " + numbers[1] + " " + numbers[0]);

    }
}
