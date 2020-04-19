package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Hippodrome {

    static Hippodrome game;

    private List<Horse> horses;

    public Hippodrome(List<Horse> list){
        horses = list;
    }

    public List<Horse> getHorses() {
        return horses;
    }

    public void run(){
        for (int i = 0; i < 100; i++){
            move();
            print();
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void move(){
        horses.forEach(Horse::move);
    }
    public void print(){
        horses.forEach(Horse::print);
        for(int i = 0; i < 10; i++) System.out.println();
    }

    public Horse getWinner(){
        return horses.stream().max(Comparator.comparingDouble(Horse::getDistance)).get();
    }

    public void printWinner(){
        System.out.printf("Winner is %s!", getWinner().getName());
    }


    public static void main(String[] args) {
        List<Horse> list = new ArrayList<>();
                Collections.addAll(list,
                        new Horse("Vistra", 3, 0),
                        new Horse("Kedar", 3, 0),
                        new Horse("Miks", 3, 0));

        game = new Hippodrome(list);
        game.run();
        game.printWinner();
    }
}
