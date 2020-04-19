package com.javarush.task.task07.task0716;

import java.util.ArrayList;
import java.util.Iterator;

/* 
Р или Л
*/

public class Solution {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();
        strings.add("роза");
        strings.add("лоза");
        strings.add("лира");
        strings = fix(strings);

        for (String string : strings) {
            System.out.println(string);
        }
    }

/*********************РЕШЕНИЕ №1***********************/

/*    public static ArrayList<String> fix(ArrayList<String> strings) {
        Iterator<String> st = strings.iterator();
        ArrayList<String> new_strings = new ArrayList<>();

        while (st.hasNext()) {
            String s = st.next();
            if (s.contains("л") && !s.contains("р")) {
                new_strings.add(s);
                new_strings.add(s);
            }
            else if (s.contains("р") && !s.contains("л")) continue;
            else new_strings.add(s);
        }
        return new_strings;
    }
    */


    /*********************РЕШЕНИЕ №2***********************/

    public static ArrayList<String> fix(ArrayList<String> strings) {

        for (int i = 0; i < strings.size(); i++){
            String s = strings.get(i);
            if (s.contains("л") && !s.contains("р")) {
                strings.add(i, s);
                i++;
            }
            else if (s.contains("р") && !s.contains("л")){
                strings.remove(i);
                i--;
            }
        }
        return strings;
    }
}