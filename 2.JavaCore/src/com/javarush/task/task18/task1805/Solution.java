package com.javarush.task.task18.task1805;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

/* 
Сортировка байт
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream file = new FileInputStream(fileName);

        Set<Integer> bytes = new HashSet<>();

        while (file.available() > 0){
            int val = file.read();
            bytes.add(val);
        }
        file.close();

        bytes.stream().sorted().forEach(s ->System.out.print(s+ " "));
    }
}
