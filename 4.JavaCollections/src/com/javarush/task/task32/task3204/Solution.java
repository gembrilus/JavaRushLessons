package com.javarush.task.task32.task3204;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/* 
Генератор паролей
*/
public class Solution {
    public static void main(String[] args) {
        ByteArrayOutputStream password = getPassword();
        System.out.println(password.toString());
    }

    public static ByteArrayOutputStream getPassword() {

            int number = (int)(Math.random() * (6561-2187) + 2187);
            String mask = Integer.toString(number, 3);
            if (!mask.contains("0") || !mask.contains("1") || !mask.contains("2")){
                return getPassword();
            }
            char[] str = mask.toCharArray();

            for (int i = 0; i < str.length; i++) {
                switch (str[i]) {
                    case '0':
                        str[i] = (char) ((int) (Math.random() * (123 - 97)) + 97);
                        break;
                    case '1':
                        str[i] = (char) ((int) (Math.random() * (91 - 65)) + 65);
                        break;
                    case '2':
                        str[i] = (char) ((int) (Math.random() * (58 - 48)) + 48);
                        break;
                    default:
                        break;
                }
            }

        try(ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write(new String(str).getBytes());
            return baos;
        } catch (IOException ignore) {
            return new ByteArrayOutputStream();
        }
    }
}