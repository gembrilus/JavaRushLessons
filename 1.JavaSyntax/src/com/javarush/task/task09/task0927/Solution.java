package com.javarush.task.task09.task0927;

import java.util.*;

/* 
Десять котов
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, Cat> map = createMap();
        Set<Cat> set = convertMapToSet(map);
        printCatSet(set);
    }

    public static Map<String, Cat> createMap() {
        HashMap<String, Cat> mapa = new HashMap<>();
        mapa.put("Имя1", new Cat("Cat1"));
        mapa.put("Имя2", new Cat("Cat2"));
        mapa.put("Имя3", new Cat("Cat3"));
        mapa.put("Имя4", new Cat("Cat4"));
        mapa.put("Имя5", new Cat("Cat5"));
        mapa.put("Имя6", new Cat("Cat6"));
        mapa.put("Имя7", new Cat("Cat7"));
        mapa.put("Имя8", new Cat("Cat8"));
        mapa.put("Имя9", new Cat("Cat9"));
        mapa.put("Имя10", new Cat("Cat10"));
        return mapa;
    }

    public static Set<Cat> convertMapToSet(Map<String, Cat> map) {
        Set<Cat> set = new HashSet<>();
    for (Cat cat: map.values()) set.add(cat);
        return set;
    }

    public static void printCatSet(Set<Cat> set) {
        for (Cat cat : set) {
            System.out.println(cat);
        }
    }

    public static class Cat {
        private String name;

        public Cat(String name) {
            this.name = name;
        }

        public String toString() {
            return "Cat " + this.name;
        }
    }


}
