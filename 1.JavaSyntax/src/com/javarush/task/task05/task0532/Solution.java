package com.javarush.task.task05.task0532;

import java.io.*;

/* 
Задача по алгоритмам
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(reader.readLine());
        int maximum = 0;
        if(N>0) {
            for (int i = 0; i < N; i++){
                int a = Integer.parseInt(reader.readLine());
                if(i == 0) maximum = a;
                else if(a > maximum) maximum = a;
            }
        }

        System.out.println(maximum);
    }
}
