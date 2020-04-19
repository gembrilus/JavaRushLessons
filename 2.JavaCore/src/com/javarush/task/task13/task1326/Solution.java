package com.javarush.task.task13.task1326;

/* 
Сортировка четных чисел из файла
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        InputStream stream = new FileInputStream(reader.readLine());
        BufferedReader streamReader = new BufferedReader(new InputStreamReader(stream));
        reader.close();

        String s;
        ArrayList<Integer> digit = new ArrayList<>();
        while ((s = streamReader.readLine()) != null) {
            int i = Integer.parseInt(s);
            if (i % 2 == 0) digit.add(i);
        }
        streamReader.close();

        Collections.sort(digit);
        for (int i : digit) System.out.println(i);
    }
}
