package com.javarush.task.task04.task0441;

/* 
Как-то средненько
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int[] temp = new int[3];
        for(int i = 0; i < 3; i++)
            temp[i] = Integer.parseInt(bf.readLine());

        Arrays.sort(temp);
        System.out.println(temp[1]);

    }
}
