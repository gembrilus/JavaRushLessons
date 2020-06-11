package com.javarush.task.task32.task3201;

import java.io.RandomAccessFile;

/*
Запись в существующий файл
*/
public class Solution {
    public static void main(String... args) {
        String fileName = args[0];
        int position = Integer.parseInt(args[1]);
        String text = args[2];

        try(RandomAccessFile file = new RandomAccessFile(fileName, "rw")){
            if (file.length() < position){
                file.seek(file.length());
            } else {
                file.seek(position);
            }
            file.write(text.getBytes());
        } catch (Exception ignore){
        }
    }
}
