package com.javarush.task.task07.task0714;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Слова в обратном порядке
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) list.add(new BufferedReader(new InputStreamReader(System.in)).readLine());

        list.remove(2);

        for (int i = 0; i<list.size(); i++){
            System.out.println(list.get(list.size()-i-1));
        }
    }
}
