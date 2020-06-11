package com.javarush.task.task35.task3509;

import java.util.*;
import java.util.stream.Collectors;

/*
Collections & Generics
*/
public class Solution {

    public static void main(String[] args) {
    }

    public static <T> ArrayList<T> newArrayList(T... elements) {
        return Arrays.stream(elements)
                .collect(
                        Collectors.toCollection(ArrayList::new)
                );
    }

    public static <T> HashSet<T> newHashSet(T... elements) {
        return Arrays.stream(elements)
                .collect(
                        Collectors.toCollection(HashSet::new)
                );
    }

    public static <K, V> HashMap<K, V> newHashMap(List<? extends K> keys, List<? extends V> values) {
        if (keys.size() != values.size()) throw new IllegalArgumentException();
        HashMap<K, V> map = new HashMap<>();
        for (int i = 0; i < keys.size(); i++){
            map.put(keys.get(i), values.get(i));
        }
        return map;
    }
}
