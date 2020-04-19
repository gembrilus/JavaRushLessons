package com.javarush.task.task28.task2805;

import java.util.concurrent.atomic.AtomicInteger;

public class MyThread extends Thread {

    private static AtomicInteger priority = new AtomicInteger(Thread.MIN_PRIORITY);

    {
        int groupPriority = Thread.currentThread().getThreadGroup().getMaxPriority();
        if(groupPriority < priority.get()){
            setPriority(groupPriority);
        }
        else{
            setPriority(priority.getAndIncrement());
        }
        if (priority.get() > Thread.MAX_PRIORITY) {
            priority = new AtomicInteger(Thread.MIN_PRIORITY);
        }
    }

    public MyThread() {
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread(ThreadGroup group, Runnable target) {
        super(group, target);
    }

    public MyThread(String name) {
        super(name);
    }

    public MyThread(ThreadGroup group, String name) {
        super(group, name);
    }

    public MyThread(Runnable target, String name) {
        super(target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name) {
        super(group, target, name);
    }

    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize) {
        super(group, target, name, stackSize);
    }

/*    public MyThread(ThreadGroup group, Runnable target, String name, long stackSize, boolean inheritThreadLocals) {
        super(group, target, name, stackSize, inheritThreadLocals);
    }*/
}
