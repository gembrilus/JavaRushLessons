package com.javarush.task.task26.task2603;

import java.util.Comparator;

/*
Убежденному убеждать других не трудно
*/
public class Solution {

    public static class CustomizedComparator<T> implements Comparator<T>{

        private Comparator<T>[] comparators;

        public CustomizedComparator(Comparator<T>...vararg){
            comparators = vararg;
        }

        @Override
        public int compare(T o1, T o2) {
            for (Comparator<T> c : comparators){
                int i = c.compare(o1, o2);
                if(i != 0 ) return i;
            }
            return 0;
        }
    }

    public static void main(String[] args) {

    }
}
