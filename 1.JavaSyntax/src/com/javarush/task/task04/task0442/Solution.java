package com.javarush.task.task04.task0442;

/* 
Суммирование
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int sum = 0;
        while (true)
        {
            int k = Integer.parseInt(bf.readLine());
            sum+=k;
            if(k == -1) {
                System.out.println(sum);
                break;
            }
        }

    }
}
