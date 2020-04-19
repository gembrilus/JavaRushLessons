package com.javarush.task.task19.task1927;

/* 
Контекстная реклама
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class Solution {
    public static TestString testString = new TestString();

    public static void main(String[] args) {

        ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(byteArray);

        PrintStream console = System.out;
        System.setOut(out);

        testString.printSomething();

        System.setOut(console);

        String[] arr = byteArray.toString().split("\n");
        for (int i = 0; i < arr.length; i++)
            if(i%2!=0){
                System.out.println(arr[i]);
                System.out.println("JavaRush - курсы Java онлайн");
            }
            else System.out.println(arr[i]);
    }

    public static class TestString {
        public void printSomething() {
            System.out.println("first");
            System.out.println("second");
            System.out.println("third");
            System.out.println("fourth");
            System.out.println("fifth");
        }
    }
}
