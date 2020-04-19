package com.javarush.task.task18.task1821;

/* 
Встречаемость символов
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws IOException {

        Map<Integer, Integer> map = new HashMap<>();
        FileReader fileReader = new FileReader(args[0]);

        while (fileReader.ready()){
            int current = fileReader.read();
            if(map.containsKey(current)) map.replace(current, map.get(current)+1);
            else map.put(current, 1);
        }
        fileReader.close();

        map.keySet().stream()
                .sorted()
                .forEach(a -> System.out.println((char) a.intValue() + " " + map.get(a)));
    }
}
