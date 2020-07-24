package com.javarush.task.task38.task3803;

/* 
Runtime исключения (unchecked exception)
*/

import java.util.Date;

public class VeryComplexClass {
    public void methodThrowsClassCastException() {
        Object obj = new Date();
        Integer i = (Integer) obj;
    }

    public void methodThrowsNullPointerException() {
        Object o = null;
        o.hashCode();
    }

    public static void main(String[] args) {
        VeryComplexClass veryComplexClass = new VeryComplexClass();
        veryComplexClass.methodThrowsNullPointerException();
    }
}
