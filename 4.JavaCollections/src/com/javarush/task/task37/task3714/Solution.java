package com.javarush.task.task37.task3714;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/* 
Древний Рим
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Input a roman number to be converted to decimal: ");
        String romanString = bufferedReader.readLine();
        System.out.println("Conversion result equals " + romanToInteger(romanString));
    }

    public static int romanToInteger(String s) {
        Map<String, Integer> map = new HashMap<>();
        map.put("I", 1);
        map.put("V", 5);
        map.put("X", 10);
        map.put("L", 50);
        map.put("C", 100);
        map.put("D", 500);
        map.put("M", 1000);

        String[] chars = new StringBuilder(s)
                .reverse()
                .toString()
                .split("");
        String previous = null;
        int result = 0;

        for (String ch: chars){
            int current = map.get(ch);
            if (previous == null){
                previous = ch;
                result = current;
            } else {
                int prev = map.get(previous);
                if (prev <= current){
                    result += current;
                } else {
                    result -= current;
                }
                previous = ch;
            }
        }

        return result;
    }
}
