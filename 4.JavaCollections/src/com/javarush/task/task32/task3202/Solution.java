package com.javarush.task.task32.task3202;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

/* 
Читаем из потока
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        StringWriter writer = getAllDataFromInputStream(new FileInputStream("testFile.log"));
        System.out.println(writer.toString());
    }

    public static StringWriter getAllDataFromInputStream(InputStream is) throws IOException {
        StringWriter writer = new StringWriter();
        if (is == null || is.available() == 0) return writer;
        byte[] buffer = new byte[8 * 1024];
        int len;
        while ((len = is.read(buffer)) != -1){
            writer.write(new String(buffer, 0, len));
        }
        return writer;
    }
}