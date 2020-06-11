package com.javarush.task.task37.task3711;

public class Computer {
    private CPU cpu = new CPU();
    private HardDrive drive = new HardDrive();
    private Memory memory = new Memory();

    public void run(){
        cpu.calculate();
        memory.allocate();
        drive.storeData();
    }
}
