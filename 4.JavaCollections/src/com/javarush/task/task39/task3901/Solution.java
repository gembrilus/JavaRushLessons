package com.javarush.task.task39.task3901;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Уникальные подстроки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Please enter your string: ");
        String s = bufferedReader.readLine();

        System.out.println("The longest unique substring length is: \n" + lengthOfLongestUniqueSubstring(s));
    }

    public static int lengthOfLongestUniqueSubstring(String s) {
        if (s == null || s.isEmpty()){
            return 0;
        }
        int max = 1;
        List<Character> characters = new ArrayList<>();
        for (int i = 0; i < s.length(); i++){
            for (int j = i; j < s.length(); j++){
                Character c = s.charAt(j);
                if (characters.contains(c) || j > s.length() - 1) {
                    max = Math.max(max, characters.size());
                    characters.clear();
                }
                characters.add(c);
            }
            max = Math.max(max, characters.size());
            characters.clear();
        }
        return max;
    }
}
