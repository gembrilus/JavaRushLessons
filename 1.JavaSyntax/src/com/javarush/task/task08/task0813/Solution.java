package com.javarush.task.task08.task0813;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/* 
20 слов на букву «Л»
*/

public class Solution {
    public static Set<String> createSet() {
        String[] arr = {"ЛАБА3", "ЛАБАЗНИК", "ЛАБИЛЬНЫЙ", "ЛАБИРИНТ", "ЛАБОРАНТ",
                        "ЛАБОРАТОРИЯ", "ЛАВА", "ЛАВА3", "ЛАВАНДА", "ЛАВАШ",
                        "ЛАВИНА", "ЛАВИРОВАТЬ", "ЛАВКА", "ЛАВОЧКА", "ЛАВОЧНИК",
                        "ЛАВР", "ЛАВРА", "ЛАВРОВИШНЯ", "ЛАВСАН", "ЛАГ"};

        Set<String> set = new HashSet<>(Arrays.asList(arr));
        return set;
    }

    public static void main(String[] args) {

    }
}
