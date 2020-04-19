package com.javarush.task.task07.task0712;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Самые-самые
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        ArrayList<String> strings = new ArrayList<>();
        for(int i =0; i<10;i++) strings.add(new BufferedReader(new InputStreamReader(System.in)).readLine());

        int max = strings.get(0).length(), min = max;
        for (int i = 1; i<strings.size(); i++){
            int temp = strings.get(i).length();
            if(temp > max) max = temp;
            if(temp < min) min = temp;
        }

        for(String s: strings)
            if(s.length() == max || s.length() == min)
            {
                System.out.println(s);
                break;
            }
    }
}
