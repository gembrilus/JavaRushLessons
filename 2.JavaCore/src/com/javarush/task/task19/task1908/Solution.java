package com.javarush.task.task19.task1908;

/* 
Выделяем числа
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String inputFileName = "";
        String outputFileName = "";

        try {
            inputFileName = reader.readLine();
            outputFileName = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try(FileReader fr = new FileReader(inputFileName);
            BufferedReader fileReader = new BufferedReader(fr);
            FileWriter fw = new FileWriter(outputFileName);
            BufferedWriter fileWriter = new BufferedWriter(fw)){
            StringBuilder s = new StringBuilder();
            while (fileReader.ready()) s.append((char) fileReader.read());

            String[] digits = s.toString().split(" ");

            for (String str: digits)
                if(str.matches("^\\d++$\n?"))
                    fileWriter.write( str + " ");

        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
