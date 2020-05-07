package com.javarush.task.task29.task2913;

import java.util.Random;
import java.util.stream.IntStream;

/* 
Замена рекурсии
*/

public class Solution {
    private static int numberA;
    private static int numberB;

    public static String getAllNumbersBetween(int a, int b) {
        IntStream stream;
        if (a <= b) {
            stream = IntStream.rangeClosed(a, b);
        } else {
            stream = IntStream.iterate(a, i -> i - 1).limit(a - b + 1);
        }

        return stream
                .collect(
                        StringBuilder::new,
                        (sb, i) -> sb.append(i).append(" "),
                        StringBuilder::append
                )
                .toString()
                .trim();
    }

    public static void main(String[] args) {
        Random random = new Random();
        numberA = random.nextInt(1000);
        numberB = random.nextInt(1000);
        System.out.println(getAllNumbersBetween(numberA, numberB));
        System.out.println(getAllNumbersBetween(numberB, numberA));
    }
}