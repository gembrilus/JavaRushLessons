package com.javarush.task.task08.task0815;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Перепись населения
*/

public class Solution {
    public static Map<String, String> createMap() {
        Map<String, String> map = new HashMap<>();
        map.put("Коенко", "Алексей");
        map.put("Ковалов", "Андрей");
        map.put("Коноваленко", "Дмитрий");
        map.put("Ковтуненко", "Сергей");
        map.put("Дитренко", "Алексей");
        map.put("Путин", "Владимир");
        map.put("Коновалов", "Алексей");
        map.put("Кононенко", "Максим");
        map.put("Дмитренко", "Алексей");
        map.put("Гаврыш", "Степан");
        return map;

    }

    public static int getCountTheSameFirstName(Map<String, String> map, String name) {
        return Collections.frequency(map.values(), name);

    }

    public static int getCountTheSameLastName(Map<String, String> map, String lastName) {
        return Collections.frequency(map.keySet(),lastName);

    }

    public static void main(String[] args) {

    }
}
