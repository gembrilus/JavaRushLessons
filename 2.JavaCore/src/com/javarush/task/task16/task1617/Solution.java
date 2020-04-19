package com.javarush.task.task16.task1617;

/* 
Отсчет на гонках
*/

public class Solution {
    public static volatile int numSeconds = 3;

    public static void main(String[] args) throws InterruptedException {
        RacingClock clock = new RacingClock();
        Thread.sleep(3500);
        clock.interrupt();
    }

    public static class RacingClock extends Thread {
        public RacingClock() {
            start();
        }

        public void run() {
            try {
                while (!isInterrupted()) {
                    if (numSeconds == 0){
                        System.out.print("Марш!");
                        break;
                    }
                    else System.out.print(numSeconds + " ");
                    numSeconds--;
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                System.out.print("Прервано!");
            }
        }
    }
}

