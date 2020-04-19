package com.javarush.task.task14.task1420;

/* 
НОД
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int A, B;
        try {
            A = Integer.parseInt(reader.readLine());
            B = Integer.parseInt(reader.readLine());
            if(A <= 0 || B <= 0) throw new NumberFormatException();
            while (A != 0 && B != 0) {
                if (A > B) A = A % B;
                else B = B % A;
            }
            System.out.println(A+B);

        } catch (NumberFormatException e){
            throw e;
        }
    }
}
