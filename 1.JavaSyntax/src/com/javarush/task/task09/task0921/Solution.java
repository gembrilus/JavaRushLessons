package com.javarush.task.task09.task0921;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Метод в try..catch
*/

public class Solution {
    public static void main(String[] args) {
        readData();
    }

    public static void readData() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> integers = new ArrayList<>();
        while (true){
            try {
                int i = Integer.parseInt(reader.readLine());
                integers.add(i);
            }
            catch (Exception e){
                for (int i: integers) System.out.println(i);
                return;
            }
        }
    }
}
