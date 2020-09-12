package com.javarush.task.task39.task3908;

import java.util.Objects;
import java.util.stream.Collectors;

/*
Возможен ли палиндром?
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isPalindromePermutation("asdasd"));
        System.out.println(isPalindromePermutation("asdasdgh"));
        System.out.println(isPalindromePermutation("asdasdg"));
        System.out.println(isPalindromePermutation("asd"));
        System.out.println(isPalindromePermutation("PiWpWoi"));
    }

    public static boolean isPalindromePermutation(String s) {
        return s.toLowerCase().chars().boxed().collect(
                Collectors.toMap(c->c, c->c, (k, v) -> null)
        ).values().stream()
                .filter(Objects::nonNull).count() <= 1L;
    }
}
