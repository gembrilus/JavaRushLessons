package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.Observer;

public class Cook extends Observable implements Observer {
    private String name;

    public Cook(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void update(Observable o, Object arg) {
        Order order = (Order) arg;
        int duration = order.getTotalCookingTime();

        registerEvent(order, duration);

        String message = String.format("Start cooking - %s, cooking time %dmin", order, duration);
        ConsoleHelper.writeMessage(message);
        setChanged();
        notifyObservers(order);
    }

    private void registerEvent(Order order, int duration){
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(
                order.getTablet().toString(),
                name,
                duration,
                order.getDishes()
        ));
    }
}
