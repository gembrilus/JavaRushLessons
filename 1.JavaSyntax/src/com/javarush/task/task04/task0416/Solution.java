package com.javarush.task.task04.task0416;

/* 
Переходим дорогу вслепую
*/

import java.io.*;
import java.util.*;

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        double t = Double.parseDouble(bf.readLine());

        double dt = t%5;
        if(dt >= 0 && dt < 3) System.out.println("зелёный");
        else if(dt >= 3 && dt < 4) System.out.println("жёлтый");
        else System.out.println("красный");

    }
}