package com.javarush.task.task08.task0812;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Cамая длинная последовательность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        List<Integer> digits = new ArrayList<>();
        for (int i = 0; i < 10; i++) digits.add(Integer.parseInt(reader.readLine()));

        int max = 1;
        int newDigit = 0;
        int size = digits.size();
        for (int i = 0, k = 1; i < digits.size(); i++) {
            int digit = digits.get(i);
            if(i == 0) newDigit = digit;
            else{
                if(newDigit == digit) k++;
                if(newDigit != digit | i == size-1){
                    newDigit = digit;
                    if(max < k) max = k;
                    k = 1;
                }
            }
        }

        System.out.println(max);
    }
}