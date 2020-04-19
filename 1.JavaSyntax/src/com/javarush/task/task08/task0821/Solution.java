package com.javarush.task.task08.task0821;

import java.util.HashMap;
import java.util.Map;

/* 
Однофамильцы и тёзки
*/

public class Solution {
    public static void main(String[] args) {
        Map<String, String> map = createPeopleList();
        printPeopleList(map);
    }

    public static Map<String, String> createPeopleList() {
        Map<String, String> map = new HashMap<>();
        map.put("Коноваленко", "Алексей");
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

    public static void printPeopleList(Map<String, String> map) {
        for (Map.Entry<String, String> s : map.entrySet()) {
            System.out.println(s.getKey() + " " + s.getValue());
        }
    }
}
