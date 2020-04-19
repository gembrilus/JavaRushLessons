package com.javarush.task.task08.task0817;

import java.util.*;

/* 
Нам повторы не нужны
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Коенко", "Алексей");
        map.put("Коновалов", "Алексей");
        map.put("Ковалов", "Андрей");
        map.put("Коноваленко", "Дмитрий");
        map.put("Ковтуненко", "Сергей");
        map.put("Дитренко", "Алексей");
        map.put("Путин", "Владимир");
        map.put("Кононенко", "Максим");
        map.put("Дмитренко", "Алексей");
        map.put("Гаврыш", "Степан");
        return map;
    }

    public static void removeTheFirstNameDuplicates(Map<String, String> map) {

        HashSet<String> list = new HashSet<>(map.values());
        for (String s : list)
            if (Collections.frequency(map.values(), s) > 1) removeItemFromMapByValue(map, s);
    }

    public static void removeItemFromMapByValue(Map<String, String> map, String value) {
        Map<String, String> copy = new HashMap<>(map);
        for (Map.Entry<String, String> pair : copy.entrySet()) {
            if (pair.getValue().equals(value)) {
                map.remove(pair.getKey());
            }
        }
    }

    public static void main(String[] args) {

    }
}
