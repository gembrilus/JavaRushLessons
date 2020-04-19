package com.javarush.task.task08.task0818;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* 
Только для богачей
*/

public class Solution {
    public static Map<String, Integer> createMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Коенко", 1000);
        map.put("Коновалов", 800);
        map.put("Ковалов", 1210);
        map.put("Коноваленко", 300);
        map.put("Ковтуненко", 350);
        map.put("Дитренко", 300);
        map.put("Путин", 500);
        map.put("Кононенко", 400);
        map.put("Дмитренко", 100);
        map.put("Гаврыш", 5000);
        return map;
    }

    public static void removeItemFromMap(Map<String, Integer> map) {
        Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
        while (iterator.hasNext()){
            int value = iterator.next().getValue();
            if(value < 500) iterator.remove();
        }
    }

    public static void main(String[] args) {

    }
}