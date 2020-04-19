package com.javarush.task.task08.task0823;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/* 
Омовение Рамы
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = reader.readLine();
        StringBuilder newString = new StringBuilder();

        for (int i = 0; i < string.length(); i++) {
            char letter = string.charAt(i);

            if (i == 0 && letter != ' ')
                newString.append(Character.toUpperCase(letter));
            else if (letter != ' ' && string.charAt(i - 1) == ' ')
                newString.append(Character.toUpperCase(letter));
            else newString.append(letter);
        }

        System.out.println(newString);
    }
}