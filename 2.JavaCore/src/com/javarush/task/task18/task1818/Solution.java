package com.javarush.task.task18.task1818;

/* 
Два в одном
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String file1 = reader.readLine();
        String file2 = reader.readLine();
        String file3 = reader.readLine();

        FileOutputStream fileOutputStream = new FileOutputStream(file1);
        FileInputStream fileInputStream1 = new FileInputStream(file2);
        FileInputStream fileInputStream2 = new FileInputStream(file3);

        while (fileInputStream1.available() > 0)
            fileOutputStream.write(fileInputStream1.read());
        while (fileInputStream2.available() > 0)
            fileOutputStream.write(fileInputStream2.read());

        fileInputStream1.close();
        fileInputStream2.close();
        fileOutputStream.close();
    }
}
