package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Cook;
import com.javarush.task.task27.task2712.kitchen.Waiter;

public class Restaurant {
    public static void main(String[] args) {
        Tablet tablet = new Tablet(1);
//        Tablet tablet2 = new Tablet(2);
        Cook cook = new Cook("Amigo");
        /*Cook cook2 = new Cook("Max");
        Cook cook3 = new Cook("Ivan");*/
        Waiter waiter = new Waiter();

        tablet.addObserver(cook);
        cook.addObserver(waiter);
/*
        tablet2.addObserver(cook2);
        cook2.addObserver(waiter);

        tablet.addObserver(cook3);
        cook3.addObserver(waiter);
       */
        tablet.createOrder();

/*        tablet2.createOrder();
        tablet.createOrder();*/

        DirectorTablet directorTablet = new DirectorTablet();
        directorTablet.printActiveVideoSet();
        directorTablet.printAdvertisementProfit();
        directorTablet.printArchivedVideoSet();
        directorTablet.printCookWorkloading();
    }
}
