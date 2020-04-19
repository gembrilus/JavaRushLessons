package com.javarush.task.task06.task0606;

import java.io.*;

/* 
Чётные и нечётные циферки
*/

public class Solution {

    public static int even;
    public static int odd;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int a = Integer.parseInt(bf.readLine());

        do {
            if (a > 10) {
                if ((a % 10) % 2 == 0) even++;
                else odd++;
            } else {
                if (a % 2 == 0) even++;
                else odd++;
            }

        } while ((a /= 10) != 0);

        System.out.println("Even: " + even + " Odd: " + odd);
    }
}
