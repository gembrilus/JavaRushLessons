package com.javarush.task.task07.task0713;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Играем в Jолушку
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<Integer> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        for (int i = 0; i < 20; i++) list.add(Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine()));

        for (int i: list){
            if(i%3 == 0) list1.add(i);
            if (i%2 == 0) list2.add(i);
            if(i%3!=0 && i%2 != 0) list3.add(i);
        }

        printList(list1);
        printList(list2);
        printList(list3);

    }

    public static void printList(ArrayList<Integer> list) {
        for (int i: list) System.out.println(i);
    }
}
