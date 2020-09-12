package com.javarush.task.task27.task2712.kitchen;

import com.javarush.task.task27.task2712.ConsoleHelper;
import com.javarush.task.task27.task2712.statistic.StatisticManager;
import com.javarush.task.task27.task2712.statistic.event.CookedOrderEventDataRow;

import java.util.Observable;
import java.util.concurrent.LinkedBlockingQueue;

public class Cook extends Observable implements Runnable {
    private String name;
    private boolean busy;
    private LinkedBlockingQueue<Order> queue;

    public Cook(String name) {
        this.name = name;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setQueue(LinkedBlockingQueue<Order> queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return name;
    }

    public void startCookingOrder(Order order) {
        busy = true;
        try {
            int duration = order.getTotalCookingTime();
            registerEvent(order, duration);
            String message = String.format("Start cooking - %s, cooking time %dmin", order, duration);
            Thread.sleep(10 * duration);
            ConsoleHelper.writeMessage(message);
            setChanged();
            notifyObservers(order);
        } catch (InterruptedException ignore){

        }
        busy = false;
    }

    private void registerEvent(Order order, int duration){
        StatisticManager.getInstance().register(new CookedOrderEventDataRow(
                order.getTablet().toString(),
                name,
                duration,
                order.getDishes()
        ));
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignore) {

            }
            if (!queue.isEmpty() && !isBusy()) {
                    Order order = queue.poll();
                    if (order != null){
                        startCookingOrder(order);
                    }
            }
        }
    }
}
