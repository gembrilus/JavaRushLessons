package com.javarush.task.task38.task3804;

/* 
Фабрика исключений
*/

public class Solution {
    public static Class getFactoryClass() {
        return Factory.class;
    }

    public static void main(String[] args) {
        Throwable t = Factory.getInstance(UserExceptionMessage.USER_DOES_NOT_EXIST);
        System.out.println(t.getMessage());
    }
}