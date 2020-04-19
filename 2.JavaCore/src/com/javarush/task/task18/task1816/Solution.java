package com.javarush.task.task18.task1816;

/* 
Английские буквы
*/

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        BufferedReader file = new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
        long count = file.lines()
                .flatMapToInt(String::chars)
                .filter(c -> Character.toString((char) c).matches("[a-zA-Z]") )
                .count();
        file.close();
        System.out.println(count);
    }
}
