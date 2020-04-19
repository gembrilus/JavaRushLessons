package com.javarush.task.task19.task1926;

/* 
Перевертыши
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args){
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = "";

        try {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(BufferedReader fileReader = new BufferedReader(new FileReader(file))){
            while(fileReader.ready()) System.out.println(new StringBuilder(fileReader.readLine()).reverse().toString());
        } catch (IOException e){e.printStackTrace();}
    }
}
