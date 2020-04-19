package com.javarush.task.task18.task1820;

/* 
Округление чисел
*/

import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        Scanner fileReader = new Scanner(new FileReader(file1))
                .useDelimiter(" ")
                .useLocale(Locale.ENGLISH);
        BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file2));

        StringBuilder doubleFromFile = new StringBuilder();
        while (fileReader.hasNext())
            doubleFromFile
                    .append(Long.toString(Math.round(Double.valueOf(fileReader.next()))))
                    .append(" ");

//        String doubleFromFile = sc.findAll("-?\\d*\\.\\d+")
//                .mapToDouble(s -> Double.parseDouble(s.group()))
//                .mapToLong(Math::round)
//                .mapToObj(Long::toString)
//                .collect(Collectors.joining(" "));

        fileWriter.write(doubleFromFile.toString());

        fileReader.close();
        fileWriter.close();

    }
}
