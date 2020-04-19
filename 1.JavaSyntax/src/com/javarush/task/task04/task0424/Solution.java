package com.javarush.task.task04.task0424;

/* 
Три числа
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = new int[3];
        for(int i =0; i <3; i++)
            numbers[i] = Integer.parseInt(bufferedReader.readLine());

        if(numbers[0] == numbers[1] && numbers[0] != numbers[2]) System.out.println(3);
        if(numbers[0] == numbers[2] && numbers[0] != numbers[1]) System.out.println(2);
        if(numbers[1] == numbers[2] && numbers[0] != numbers[1]) System.out.println(1);



    }
}
