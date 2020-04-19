package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {

    private Thread thread;

    public LoggingStateThread(Thread target) {
        thread = target;
    }

    @Override
    public void run() {
        State status = null;
        while (status != State.TERMINATED){
            if(thread.getState() != status){
                status = thread.getState();
                System.out.println(status);
            }
        }
    }
}
