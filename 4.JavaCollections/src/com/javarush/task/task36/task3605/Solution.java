package com.javarush.task.task36.task3605;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.TreeSet;

/* 
Использование TreeSet
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        TreeSet<String> set = new TreeSet<>();
        Files.readAllLines(Paths.get(args[0])).stream()
                .flatMap(s -> Arrays.stream(s.split("")))
                .filter(s -> s.matches("\\w"))
                .map(String::toLowerCase)
                .forEach(set::add);

        Iterator<String> iterator = set.iterator();
        int count = Math.min(set.size(), 5);
        while (iterator.hasNext() && count-- > 0) {
            System.out.print(iterator.next());
        }
    }
}
