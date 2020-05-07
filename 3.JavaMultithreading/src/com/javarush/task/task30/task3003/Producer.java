package com.javarush.task.task30.task3003;

import java.util.concurrent.TransferQueue;
import java.util.stream.IntStream;

public class Producer implements Runnable {
    private TransferQueue<ShareItem> queue;

    public Producer(TransferQueue<ShareItem> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        IntStream.rangeClosed(1, 9).forEach(i -> {
            System.out.format("Элемент 'ShareItem-%d' добавлен\n", i);
            queue.offer(new ShareItem("ShareItem-"+i, i));

            try {
                Thread.sleep(100);
            } catch (InterruptedException ignore) {
            }

            if(queue.hasWaitingConsumer()){
                System.out.format("Consumer в ожидании!\n");
            }

        });
    }
}
