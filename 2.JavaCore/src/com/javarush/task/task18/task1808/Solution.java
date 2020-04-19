package com.javarush.task.task18.task1808;

/* 
Разделение файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        FileInputStream fileReader = new FileInputStream(file1);
        FileOutputStream fileWriter1 = new FileOutputStream(file2);
        FileOutputStream fileWriter2 = new FileOutputStream(file3);

        int count = fileReader.available();
        while (fileReader.available() > 0){
            if(fileReader.available() > count/2) fileWriter1.write(fileReader.read());
            else fileWriter2.write(fileReader.read());
        }

        fileReader.close();
        fileWriter1.close();
        fileWriter2.close();
    }
}
