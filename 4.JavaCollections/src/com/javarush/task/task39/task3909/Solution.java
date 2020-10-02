package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("maxim", "") + " expected: false");
        System.out.println(isOneEditAway("maxim", "taxit") + " expected: false");
        System.out.println(isOneEditAway("maxim", "maxit") + " expected: true");
        System.out.println(isOneEditAway("maxim", "maxam") + " expected: true");
        System.out.println(isOneEditAway("ma", "max") + " expected: true");
        System.out.println(isOneEditAway("m", "ma") + " expected: true");
        System.out.println(isOneEditAway("m", "am") + " expected: true");
        System.out.println(isOneEditAway("maxim", "maxi") + " expected: true");
        System.out.println(isOneEditAway("maxim", "taximka") + " expected: false");
        System.out.println(isOneEditAway("maxim", "maimuum") + " expected: false");
        System.out.println(isOneEditAway("maxim", "mixamaaa") + " expected: false");
        System.out.println(isOneEditAway("am", "mak") + " expected: false");
        System.out.println(isOneEditAway("am", "mmm") + " expected: false");
        System.out.println(isOneEditAway("12", "1") + " expected: true");
        System.out.println(isOneEditAway("1", "12") + " expected: true");
        System.out.println(isOneEditAway("KissMyShinyReactor!", "KissMyShinyReactor") + " expected: true");
        System.out.println(isOneEditAway("KissMyShinyReactor", "KissMyShinyReactor!") + " expected: true");
    }


    public static boolean isOneEditAway(String first, String second) {
        int len1 = first.length();
        int len2 = second.length();
        int difference = Math.abs(len1 - len2);

        if (difference > 1) return false;

        if (first.isEmpty() && second.isEmpty()) return true;
        if (first.equals(second)) return true;

        StringBuilder max = (len1 >= len2) ? new StringBuilder(first) : new StringBuilder(second);
        StringBuilder min = (len1 < len2) ? new StringBuilder(first) : new StringBuilder(second);

        for (int i = 0; i < min.length(); i++) {
            if (max.charAt(i) != min.charAt(i)) {
                max.deleteCharAt(i);
                if (difference == 0) {
                    min.deleteCharAt(i);
                }
                break;
            }
        }
        if (max.length() != min.length()) max.deleteCharAt(max.length() - 1);
        return max.toString().equals(min.toString());
    }
}
