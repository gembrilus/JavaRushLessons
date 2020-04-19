package com.javarush.task.task20.task2025;

import java.util.Set;
import java.util.TreeSet;

/*
Алгоритмы-числа
*/
public class Solution {
    public static long[] getNumbers(long N) {
        if (N <= 1) return new long[0];

        Set<Long> temp = new TreeSet<>();

        int p = getDigitsCount(N);
        long[][] matrix = new long[10][p];
        for (int i = 0; i < 10; i++){
            for(int j = 0; j < p; j++){
                matrix[i][j] = pow(i, j+1);
            }
        }

        for (long i = 1; i < (N < 10 ? N : 10); i++){
            temp.add(i);
        }
        for (int i = 3; i <= p; i++){
            run(0, 1, i, N, temp, matrix);
        }

        long[] result = new long[temp.size()];
        int i = 0;
        for (long l : temp){
            result[i++] = l;
        }
        return result;
    }

    private static void run(long number, int first, int pos, long N, Set<Long> set, long[][] m) {
        for (int i = first; i < 10; i++) {
            long n = i * pow(10, pos - 1) + number;
            if (n >= N || n < 0) return;
            int p = getDigitsCount(n);
            long arm = eval(n, p, m);
            if(getDigitsCount(arm) > p) continue;
            if (eval(arm, getDigitsCount(arm), m) == arm && arm < N){
                set.add(arm);
            }
            if (pos > 1){
                run(n, i, pos - 1, N, set, m);
            }
        }
    }

    private static int getDigitsCount(long n){
        int count = 0;
        while (n > 0) {
            n /= 10;
            count++;
        }
        return count;
    }

    //вычисляем сумму степеней цифр в числе
    private static long eval(long number, int pow, long[][] matrix) {
        long temp = 0;
        while (number > 0){
            temp += matrix[(int) (number%10)][pow-1];
            number /= 10;
        }
        return temp;
    }

    private static long pow(long num, int power) {
        long temp = 1;
        for (int i = 0; i < power; i++){
            temp *= num;
        }
        return temp;
    }

    public static void main(String[] args) {
        long start = System.nanoTime();
        long[] array = getNumbers(Long.MAX_VALUE);
        long end = System.nanoTime() - start;
        long memory = Runtime.getRuntime().totalMemory()-Runtime.getRuntime().freeMemory();
        System.out.printf("Time = %.4f%nMemory = %d%n", end / 1_000_000_000.0, memory / 1048576);
        System.out.printf("Размер массива = %d%n", array.length);
        for (long a : array) System.out.print(a + " ");
    }
}
