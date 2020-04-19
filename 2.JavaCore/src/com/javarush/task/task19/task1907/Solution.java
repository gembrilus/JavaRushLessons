package com.javarush.task.task19.task1907;

/* 
Считаем слово
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = "";

        try {
            fileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        int count = 0;
        StringBuilder result = new StringBuilder();
        try(FileReader fileReader = new FileReader(fileName)) {
            while (fileReader.ready()){
                result.append((char) fileReader.read());
            }
            String[] words = result.toString().split("\\W+");
            for (String s: words)
                if(s.equals("world")) count++;
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(count);
    }
}
