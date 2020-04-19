package com.javarush.task.task19.task1909;

/* 
Замена знаков
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName1 = "";
        String fileName2 = "";

        try {
            fileName1 = reader.readLine();
            fileName2 = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        try(BufferedReader in = new BufferedReader(new FileReader(fileName1));
            BufferedWriter out = new BufferedWriter(new FileWriter(fileName2))){
            in.lines().map(s -> s.replace(".", "!")).forEach(s -> {
                try {
                    out.write(s + "\n");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
