package com.javarush.task.task18.task1819;

/* 
Объединение файлов
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();

        FileInputStream fileInputStream1 = new FileInputStream(file1);

        int count = 0;
        byte[] bytes = new byte[fileInputStream1.available()];
        while (fileInputStream1.available() > 0)
            count = fileInputStream1.read(bytes);

        fileInputStream1.close();

        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileInputStream fileInputStream2 = new FileInputStream(file2);

        while (fileInputStream2.available() > 0)
            fileOutputStream.write(fileInputStream2.read());


            fileOutputStream.write(bytes);

        fileInputStream1.close();
        fileInputStream2.close();
        fileOutputStream.close();

    }
}
