package com.javarush.task.task22.task2208;

import java.util.Map;
import java.util.stream.Collectors;

/*
Формируем WHERE
*/
public class Solution {
    public static void main(String[] args) {
//        Map<String, String> map = new HashMap<>();
//        map.put("name", "Ivanov");
//        map.put("country", "Ukraine");
//        map.put("city", "Kiev");
//        map.put("age", null);
//
//        System.out.println(getQuery(map));
    }
    public static String getQuery(Map<String, String> params) {
        return params.entrySet().stream()
                .filter(entry -> entry.getValue() != null)
                .map(value -> value.getKey() + " = '" + value.getValue() + "'")
                .collect(Collectors.joining(" and "));
    }
}
