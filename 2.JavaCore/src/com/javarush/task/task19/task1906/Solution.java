package com.javarush.task.task19.task1906;

/* 
Четные символы
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputfile = "";
        String outputFile = "";

        try {
            inputfile = reader.readLine();
            outputFile = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileReader fileReader = new FileReader(inputfile);
        FileWriter fileWriter = new FileWriter(outputFile)){
            int count = 1;
            while(fileReader.ready()){
                int a = fileReader.read();
                if(count%2 == 0) fileWriter.write(a);
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
