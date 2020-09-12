package com.javarush.task.task27.task2712;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomOrderGeneratorTask implements Runnable {
    private List<Tablet> tablets;
    private int interval;

    public RandomOrderGeneratorTask(List<Tablet> tablets, int interval) {
        this.tablets = tablets;
        this.interval = interval;
    }

    @Override
    public void run() {
        if (tablets.isEmpty()) return;
        try {
            while (!Thread.currentThread().isInterrupted()){
                Tablet tablet = tablets.get(ThreadLocalRandom.current().nextInt(tablets.size()));
                tablet.createTestOrder();
                Thread.sleep(interval);
            }
        } catch (InterruptedException ignore){

        }
    }
}
