package com.javarush.task.task32.task3210;

/* 
Используем RandomAccessFile
*/

import java.io.RandomAccessFile;

public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int number = Integer.parseInt(args[1]);
        String text = args[2];

        try(RandomAccessFile file = new RandomAccessFile(fileName, "rw")){
            int textSize = text.getBytes().length;
            byte[] buffer = new byte[textSize];
            file.seek(number);

            file.read(buffer, 0, textSize);

            file.seek(file.length());
            if (new String(buffer).equals(text)){
                file.write("true".getBytes());
            } else {
                file.write("false".getBytes());
            }
        } catch (Exception ignore){
        }
    }
}
