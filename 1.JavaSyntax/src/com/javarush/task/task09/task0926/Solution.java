package com.javarush.task.task09.task0926;

import java.util.ArrayList;

/* 
Список из массивов чисел
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<int[]> list = createList();
        printList(list);
    }

    public static ArrayList<int[]> createList() {
        ArrayList<int[]> table = new ArrayList<>();
        table.add(new int[]{1, 2, 4, 6, 7});
        table.add(new int[]{1, 2});
        table.add(new int[]{1, 2, 4, 6});
        table.add(new int[]{1, 2, 4, 6, 7, 10, 2});
        table.add(new int[]{});
        return table;
    }

    public static void printList(ArrayList<int[]> list) {
        for (int[] array : list) {
            for (int x : array) {
                System.out.println(x);
            }
        }
    }
}
