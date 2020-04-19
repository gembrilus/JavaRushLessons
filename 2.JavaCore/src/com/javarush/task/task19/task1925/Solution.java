package com.javarush.task.task19.task1925;

/* 
Длинные слова
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length !=2) return;
        try(BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
            FileWriter fileWriter = new FileWriter(args[1])){
            while (fileReader.ready()){
                String tmp = fileReader.lines()
                        .flatMap(str -> Arrays.stream(str.split(" ")))
                        .filter(s -> s.length() > 6)
                        .collect(Collectors.joining(","));
                fileWriter.write(tmp);
            }
        }
    }
}