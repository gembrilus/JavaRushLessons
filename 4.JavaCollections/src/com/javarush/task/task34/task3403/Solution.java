package com.javarush.task.task34.task3403;

/* 
Разложение на множители с помощью рекурсии
*/
public class Solution {
    public void recurse(int n) {
        for (int i = 2;n >= i; i++){
            if (n % i == 0){
                System.out.format("%d ", i);
                recurse(n / i);
                break;
            }
        }
    }
}
