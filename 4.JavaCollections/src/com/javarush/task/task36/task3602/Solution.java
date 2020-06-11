package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

/*
Найти класс по описанию Ӏ Java Collections: 6 уровень, 6 лекция
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] classes = Collections.class.getDeclaredClasses();
        for (Class<?> clazz: classes){
            if (List.class.isAssignableFrom(clazz)){
                try {
                    Method method = clazz.getDeclaredMethod("get", int.class);
                    Constructor<?> constructor = clazz.getDeclaredConstructor();
                    constructor.setAccessible(true);
                    List<?> list = (List<?>) constructor.newInstance();
                    Object obj = list.get(0);
                } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException ignore) {
                } catch (IndexOutOfBoundsException e){
                    return clazz;
                }
            }
        }
        return null;
    }
}
