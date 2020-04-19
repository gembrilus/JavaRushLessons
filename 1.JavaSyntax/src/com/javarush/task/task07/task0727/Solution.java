package com.javarush.task.task07.task0727;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/* 
Меняем функциональность
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        ArrayList<String> strings = new ArrayList<String>();
        while (true) {
            String string = reader.readLine();
            if (string == null || string.isEmpty()) break;
            else {
                if (string.length()%2 == 0){
                    strings.add(string);
                    strings.add(string);
                }
                else {
                    strings.add(string);
                    strings.add(string);
                    strings.add(string);
                }
            }
        }

        for (int i = 0, k = 0, l = 0; i < strings.size(); i++) {
            if(k == 2 || l == 3) {
                k =0;
                l = 0;
                System.out.println();
            }
            if(strings.get(i).length()%2 == 0 && k < 2) {
                k++;
                System.out.print(strings.get(i)+" ");
            }
            if(strings.get(i).length()%2 != 0 && l <3){
                l++;
                System.out.print(strings.get(i)+" ");
            }
        }
    }
}
