package com.javarush.task.task36.task3610;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class MyMultiMap<K, V> extends HashMap<K, V> implements Cloneable, Serializable {
    static final long serialVersionUID = 123456789L;
    private HashMap<K, List<V>> map;
    private int repeatCount;

    public MyMultiMap(int repeatCount) {
        this.repeatCount = repeatCount;
        map = new HashMap<>();
    }

    @Override
    public int size() {
        return (int) map.values().stream()
                .mapToLong(List::size)
                .sum();
    }

    @Override
    public V put(K key, V value) {
        List<V> currentValue  = map.get(key);
        if (currentValue == null){
            List<V> list = new ArrayList<>();
            list.add(value);
            map.put(key, list);
            return null;
        } else {
            int size = currentValue.size();
            V lastValue = currentValue.get(size-1);
            if (size < repeatCount){
                currentValue.add(value);
                map.put(key, currentValue);
            } else {
                currentValue.remove(0);
                currentValue.add(value);
            }
            return lastValue;
        }
    }

    @Override
    public V remove(Object key) {
        if (!containsKey(key)) return null;
        List<V> values = map.get(key);
        V element = values.get(0);
        values.remove(0);
        if (values.isEmpty()){
            map.remove(key);
        } else {
            map.put((K) key, values);
        }
        return element;
    }

    @Override
    public Set<K> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<V> values() {
        return map.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.values().stream()
                .flatMap(List::stream)
                .anyMatch(value::equals);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        for (Map.Entry<K, List<V>> entry : map.entrySet()) {
            sb.append(entry.getKey());
            sb.append("=");
            for (V v : entry.getValue()) {
                sb.append(v);
                sb.append(", ");
            }
        }
        String substring = sb.substring(0, sb.length() - 2);
        return substring + "}";
    }
}