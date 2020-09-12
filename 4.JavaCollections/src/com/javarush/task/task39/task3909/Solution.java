package com.javarush.task.task39.task3909;

/* 
Одно изменение
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(isOneEditAway("maxim", "") + " expected: false");
        System.out.println(isOneEditAway("maxim", "taxit") + " expected: false");
        System.out.println(isOneEditAway("maxim", "maxit") + " expected: true");
        System.out.println(isOneEditAway("maxim", "maim") + " expected: true");
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
        String subFirst = first;
        String subSecond = second;
        for (int i = 0; i <= first.length(); i++) {
            for (int j = 0; j <= second.length(); j++) {
                if (subFirst.equals(subSecond)) return true;
                if (j == second.length()) {
                    subSecond = second;
                    break;
                }
                subSecond = removeCharByIndex(second, j);
            }
            if (i == first.length()) {
                break;
            }
            subFirst = removeCharByIndex(first, i);
        }
        return false;
    }

    private static String removeCharByIndex(String origin, int i){
        StringBuilder builder = new StringBuilder(origin);
        builder.deleteCharAt(i);
        return builder.toString();
    }
}
