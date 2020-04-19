package com.javarush.task.task07.task0718;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Проверка на упорядоченность
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> list = new ArrayList<>();
        int pos = -1;
        String temp = null;
        for (int i = 0; i < 10; i++) {
            String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
            list.add(s);
            if (i == 0) temp = s;
            if (i > 0 && pos == -1) {
                if (s.length() < temp.length()) pos = i;
                else temp = s;
            }
        }
        if (pos != -1) System.out.println(pos);
    }
}

