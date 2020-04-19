package com.javarush.task.task20.task2014;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/* 
Serializable Solution
*/
public class Solution implements Serializable {
    public static void main(String[] args) {
        try {
            File file = new File("/home/maksim/1/temp");
            FileInputStream fio = new FileInputStream(file);
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fout);

            Solution savedObject = new Solution(10);
            out.writeObject(savedObject);
            out.flush();

            Solution savedObject2 = new Solution(5);
            ObjectInputStream io = new ObjectInputStream(fio);
            Solution loadedObject = (Solution) io.readObject();

            System.out.println(savedObject.string.equals(loadedObject.string));


        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        System.out.println(new Solution(4));
    }

    private transient final String pattern = "dd MMMM yyyy, EEEE";
    private transient Date currentDate;
    private transient int temperature;
    String string;

    public Solution(int temperature) {
        this.currentDate = new Date();
        this.temperature = temperature;

        string = "Today is %s, and the current temperature is %s C";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        this.string = String.format(string, format.format(currentDate), temperature);
    }

    @Override
    public String toString() {
        return this.string;
    }
}
