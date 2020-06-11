package com.javarush.task.task33.task3310;

import com.javarush.task.task33.task3310.strategy.*;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution {
    public static void main(String[] args) {
        testStrategy(new HashMapStorageStrategy(),10000);
        testStrategy(new OurHashMapStorageStrategy(), 10000);
        testStrategy(new FileStorageStrategy(), 1000);
        testStrategy(new OurHashBiMapStorageStrategy(), 10000);
        testStrategy(new HashBiMapStorageStrategy(), 10000);
        testStrategy(new DualHashBidiMapStorageStrategy(), 10000);
    }

    public static Set<Long> getIds(Shortener shortener, Set<String> strings){
        return strings.stream()
                .map(shortener::getId)
                .collect(Collectors.toSet());
    }

    public static Set<String> getStrings(Shortener shortener, Set<Long> keys){
        return keys.stream()
                .map(shortener::getString)
                .collect(Collectors.toSet());
    }

    public static void testStrategy(StorageStrategy strategy, long elementsNumber){
        Helper.printMessage(strategy.getClass().getSimpleName());
        Shortener shortener = new Shortener(strategy);
        Set<String> randomStrings = Stream.generate(Helper::generateRandomString)
                .limit(elementsNumber)
                .collect(Collectors.toSet());

        long startTime = new Date().getTime();
        Set<Long> keys = getIds(shortener, randomStrings);
        long endTime = new Date().getTime();
        Helper.printMessage(String.format("%d", endTime - startTime));

        long startTime2 = new Date().getTime();
        Set<String> strings = getStrings(shortener, keys);
        long endTime2 = new Date().getTime();
        Helper.printMessage(String.format("%d", endTime2 - startTime2));

        if (strings.size() == randomStrings.size()) {
            Helper.printMessage("Тест пройден.");
        } else {
            Helper.printMessage("Тест не пройден.");
        }
    }
}
