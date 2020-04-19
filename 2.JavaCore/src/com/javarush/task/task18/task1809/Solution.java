package com.javarush.task.task18.task1809;

/* 
Реверс файла
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream fileReader = new FileInputStream(file1);
        FileOutputStream fileWriter = new FileOutputStream(file2);

        byte[] bytes = new byte[fileReader.available()];
        while (fileReader.available() > 0)
            fileReader.read(bytes);

        for(int i = 0; i < bytes.length/2; i++) {
            byte temp = bytes[i];
            bytes[i] = bytes[bytes.length-i-1];
            bytes[bytes.length-i-1] = temp;
        }
        fileWriter.write(bytes);

        fileReader.close();
        fileWriter.close();
    }
}
