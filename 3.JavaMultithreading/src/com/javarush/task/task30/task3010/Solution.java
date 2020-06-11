package com.javarush.task.task30.task3010;

/* 
Минимальное допустимое основание системы счисления
*/

import java.math.BigInteger;

public class Solution {
    public static void main(String[] args) {
        final int LOWER_SYSTEM = 2;
        final int UPPER_SYSTEM = 36;
        try {
            String arg = args[0];
            if (!arg.matches("\\w*")) {
                System.out.println("incorrect");
            }
            for (int i = LOWER_SYSTEM; i <= UPPER_SYSTEM; i++) {
                try {
                    new BigInteger(arg, i);
                    System.out.println(i);
                    break;
                } catch (NumberFormatException ignore) {
                }
            }
        } catch (Exception e){
            System.out.println("incorrect");
        }

    }
}