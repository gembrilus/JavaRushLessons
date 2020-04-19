package com.javarush.task.task04.task0437;

/* 
Треугольник из восьмерок
*/

public class Solution {
    public static void main(String[] args) {
        for(int k = 0; k < 10; k++) {
            for (int i = 0; i < k+1; i++)
                System.out.print(8);
            System.out.println();
        }

    }
}
