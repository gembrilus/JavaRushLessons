package com.javarush.task.task19.task1922;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/* 
Ищем нужные строки
*/

public class Solution {
    public static List<String> words = new ArrayList<String>();

    static {
        words.add("файл");
        words.add("вид");
        words.add("В");
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        reader.close();
        Pattern p = Pattern.compile(words.stream()
                .collect(Collectors.joining("(?=\\s+|$|\\p{P}))|((?<=|\\s)", "((?<=|\\s)", "(?=\\s+|$|\\p{P}))")));  // (?<=|\s)файл(?=\s+|$|\p{P})
        try(FileReader io = new FileReader(file);
        BufferedReader fileReader = new BufferedReader(io)){
            while (fileReader.ready()) {
                String s = fileReader.readLine();
                Matcher m = p.matcher(s);
                int count=0;
                while (m.find()) count++;
                if(count == 2) System.out.println(s);
            }
        }
    }
}
