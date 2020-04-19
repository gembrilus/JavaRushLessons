package com.javarush.task.task19.task1920;

/* 
Самый богатый
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

public class Solution {
    public static void main(String[] args) {
        if(args.length != 1) return;
        Map<String, Double> strings = new TreeMap<>();
        try(FileReader io = new FileReader(args[0]);
            BufferedReader fileReader = new BufferedReader(io)){
            while (fileReader.ready()){
                String[] temp = fileReader.readLine().split(" ");
                double v = Double.valueOf(temp[1]);
                strings.merge(temp[0], v, (a, b) -> a + b);
            }
        } catch (IOException e1) { e1.printStackTrace(); }

        double max = strings.values()
                .stream()
                .max(Double::compareTo).get();

        strings.keySet().stream()
                .filter(a -> strings.get(a) == max)
                .forEach(s-> System.out.print(s + " "));
    }
}
