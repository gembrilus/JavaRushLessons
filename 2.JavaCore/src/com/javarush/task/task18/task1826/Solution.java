package com.javarush.task.task18.task1826;

/* 
Шифровка
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {

        String fileName = args[1];
        String outputFileName = args[2];
        try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(fileName));
        BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(outputFileName))) {

            switch (args[0]) {
                case "-e":
                    while (in.available() > 0) {
                        int byt = in.read();
                        int encryptedByte = byt<=253?byt+2:byt-253;
                        out.write(encryptedByte);
                    }
                case "-d":
                    while (in.available() > 0) {
                        int byt = in.read();
                        int decryptedByte = byt>=2?byt-2:byt+253;
                        out.write(decryptedByte);
                    }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
