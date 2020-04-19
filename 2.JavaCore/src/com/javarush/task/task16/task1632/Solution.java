package com.javarush.task.task16.task1632;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Solution {
    public static List<Thread> threads = new ArrayList<>(5);

    static {
        threads.add(new Dredy1());
        threads.add(new Dredy2());
        threads.add(new Dredy3());
        threads.add(new Dredy4());
        threads.add(new Dredy5());
    }

    public static class Dredy1 extends Thread {
        @Override
        public void run() {
            while (true) {
            }
        }
    }

    public static class Dredy2 extends Thread {
        @Override
        public void run() {
            try {
                throw new InterruptedException();
            } catch (InterruptedException e) {
                System.out.println("InterruptedException");
            }
        }
    }

    public static class Dredy3 extends Thread {
        @Override
        public void run() {
            while (true) {
                try {
                    System.out.println("Ура");
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }

    public static class Dredy4 extends Thread implements Message {
        @Override
        public void showWarning() { if(isAlive()) interrupt(); }

        @Override
        public void run() { while (!isInterrupted()){} }
    }


    public static class Dredy5 extends Thread {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int sum = 0;

        @Override
        public void run() {
            while (true) {
                try {
                    String s = reader.readLine();
                    if (s.equals("N")) break;
                    sum += Integer.parseInt(s);
                } catch (IOException e) {
                }
            }
            System.out.println(sum);
        }
    }

    public static void main(String[] args) {

//        for (Thread t : threads) t.start();

    }
}