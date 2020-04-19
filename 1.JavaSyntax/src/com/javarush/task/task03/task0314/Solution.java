package com.javarush.task.task03.task0314;

/* 
Таблица умножения
*/

public class Solution {
    public static void main(String[] args) {
        for(int i = 1; i<=10; i++) {
            for (int k = 1; k <= 10; k++)
                System.out.print(k * i + " ");
            System.out.println();
        }
    }
}
