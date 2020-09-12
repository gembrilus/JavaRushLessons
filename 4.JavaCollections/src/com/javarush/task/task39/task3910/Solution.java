package com.javarush.task.task39.task3910;

/* 
isPowerOfThree
*/
public class Solution {
    public static void main(String[] args){
        System.out.println(isPowerOfThree(6));
        System.out.println(isPowerOfThree(9));
        System.out.println(isPowerOfThree(12));
        System.out.println(isPowerOfThree(36));
        System.out.println(isPowerOfThree(27));
    }

    public static boolean isPowerOfThree(int n) {
        if (n < 1) return false;
        if (n == 1) return true;
        int i = 3;
        while (i <= n){
            if (i == n) return true;
            i *= 3;
        }
        return false;
    }
}
