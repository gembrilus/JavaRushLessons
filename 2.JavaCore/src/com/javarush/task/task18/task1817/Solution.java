package com.javarush.task.task18.task1817;

/* 
Пробелы
*/

//import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
//import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws IOException {

        FileInputStream inputStream = new FileInputStream(args[0]);
        int countSpaces = 0;
        int count = inputStream.available();
        while (inputStream.available() > 0){
            if(inputStream.read() == 32) countSpaces++;
        }
        inputStream.close();
        System.out.printf("%.2f", 1.0*countSpaces/count*100);


//        FileInputStream inputStream = new FileInputStream(args[0]);
//        BufferedReader file = new BufferedReader(new InputStreamReader(inputStream));
//        int count = inputStream.available();
//
//        long countSpace = file.lines().flatMapToInt(String::chars).filter(c -> Character.toString((char) c).equals(" ")).count();
//
//        file.close();
//        System.out.printf("%.2f", 1.0*countSpace/count*100);
    }
}
