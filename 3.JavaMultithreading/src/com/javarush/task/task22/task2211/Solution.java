package com.javarush.task.task22.task2211;

import java.io.*;
import java.nio.charset.Charset;

/* 
Смена кодировки
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length == 2) {
            Charset win1251 = Charset.forName("Windows-1251");
            Charset utf8 = Charset.forName("UTF-8");
            try(BufferedReader freader = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]), win1251));
            BufferedWriter fwriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), utf8));) {
                while (freader.ready()) {
                    fwriter.write(freader.readLine());
                }
            } catch (IOException e){e.printStackTrace();}
        }
    }
}
