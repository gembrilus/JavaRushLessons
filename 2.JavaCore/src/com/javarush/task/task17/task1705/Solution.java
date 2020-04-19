package com.javarush.task.task17.task1705;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Сад-огород
*/

public class Solution {

    public static void main(String[] args) {
/*
        Garden garden = new Garden();
        Runnable thread1 = new ThreadAddedFruit(garden);
        try {
            ((ThreadAddedFruit) thread1).t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable thread2 = new ThreadRemoveFruit(garden);
        Runnable thread3 = new ThreadAddedVegetble(garden);
        try {
            ((ThreadAddedVegetble) thread3).t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runnable thread4 = new ThreadRemoveVegetables(garden);

*/


    }

    public static class Garden {

        public final List<String> fruits = new ArrayList<String>();
        public final List<String> vegetables = new ArrayList<String>();

        public synchronized void addFruit(int index, String fruit) {
            fruits.add(index, fruit);
        }

        public synchronized void removeFruit(int index) {
            fruits.remove(index);
        }

        public synchronized void addVegetable(int index, String vegetable) {
            vegetables.add(index, vegetable);
        }

        public synchronized void removeVegetable(int index) {
            vegetables.remove(index);
        }
    }

//    static class ThreadAddedFruit implements Runnable{
//        private Thread t;
//        private Garden g;
//        String [] fruits = {"melone", "apple", "pear", "orange"};
//
//        public ThreadAddedFruit(Garden g) {
//            this.t = new Thread(this);
//            this.g = g;
//            t.start();
//        }
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName() + " is started");
//            Arrays.stream(fruits).forEach(fruit -> g.addFruit(0, fruit));
//            System.out.println(Thread.currentThread().getName() + " is stoped");
//        }
//    }
//
//    static class ThreadRemoveFruit implements Runnable{
//        private Thread t;
//        private Garden g;
//
//        public ThreadRemoveFruit(Garden g) {
//            this.t = new Thread(this);
//            this.g = g;
//            t.start();
//        }
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName() + " is started");
//            g.removeFruit(0);
//            System.out.println(Thread.currentThread().getName() + " is stoped");
//        }
//    }
//
//    static class ThreadAddedVegetble implements Runnable{
//        private Thread t;
//        private Garden g;
//        String [] vegetables = {"watermelone", "potato", "tomato", "cucumber"};
//
//        public ThreadAddedVegetble(Garden g) {
//            this.t = new Thread(this);
//            this.g = g;
//            t.start();
//        }
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName() + " is started");
//            Arrays.stream(vegetables).forEach(veg -> g.addVegetable(0, veg));
//            System.out.println(Thread.currentThread().getName() + " is stoped");
//        }
//    }
//
//    static class ThreadRemoveVegetables implements Runnable{
//        private Thread t;
//        private Garden g;
//
//        public ThreadRemoveVegetables(Garden g) {
//            this.t = new Thread(this);
//            this.g = g;
//            t.start();
//        }
//
//        @Override
//        public void run() {
//            System.out.println(Thread.currentThread().getName() + " is started");
//           g.removeVegetable(0);
//            System.out.println(Thread.currentThread().getName() + " is stoped");
//        }
//    }
}
