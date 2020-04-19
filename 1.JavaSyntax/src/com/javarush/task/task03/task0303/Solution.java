package com.javarush.task.task03.task0303;

/* 
Обмен валют
*/

public class Solution {
    public static void main(String[] args) {
        System.out.println(convertEurToUsd(100, 1.12));
        System.out.println(convertEurToUsd(150, 1.129));
    }

    public static double convertEurToUsd(int eur, double course) {
        return eur*course;

    }
}
