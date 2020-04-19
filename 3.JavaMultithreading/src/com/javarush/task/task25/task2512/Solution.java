package com.javarush.task.task25.task2512;

/* 
Живем своим умом
*/
public class Solution implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        t.interrupt();
        StringBuilder s = new StringBuilder(String.format("%s: %s%n", e.getClass().getName(), e.getMessage()));
        while (true){
            e = e.getCause();
            if(e == null) break;
            s = new StringBuilder(String.format("%s: %s%n", e.getClass().getName(), e.getMessage())).append(s);
        }
        System.out.print(s.toString());
    }

    public static void main(String[] args) throws Exception {
//        Thread.currentThread().setUncaughtExceptionHandler(new Solution());
//        throw new Exception("ABC", new RuntimeException("DEF", new IllegalAccessException("GHI")));
    }
}
