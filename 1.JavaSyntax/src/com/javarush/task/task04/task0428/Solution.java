package com.javarush.task.task04.task0428;

/* 
Положительное число
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int k = 0;
        for(int i =0; i < 3; i++)
        {
            if(Integer.parseInt(bufferedReader.readLine()) > 0) k++;
        }
        System.out.println(k);
    }
}
