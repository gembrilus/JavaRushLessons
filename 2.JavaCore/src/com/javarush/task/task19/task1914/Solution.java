package com.javarush.task.task19.task1914;

/* 
Решаем пример
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        ByteArrayOutputStream result = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(result);

        PrintStream console = System.out;
        System.setOut(out);

        testString.printSomething();

        System.setOut(console);

        String [] s = result.toString().split(" ");
        int eval = 0;
        switch (s[1]){
            case "-":   eval = Integer.valueOf(s[0]) - Integer.valueOf(s[2]); break;
            case "+":   eval = Integer.valueOf(s[0]) + Integer.valueOf(s[2]); break;
            case "*":   eval = Integer.valueOf(s[0]) * Integer.valueOf(s[2]);
        }
        System.out.println(s[0] + " " + s[1] + " " + s[2] + " = " + eval);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("3 + 6 = ");
        }
    }
}

