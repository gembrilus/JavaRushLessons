package com.javarush.task.task13.task1319;

import java.io.*;

/* 
Писатель в файл с консоли
*/

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter out = new BufferedWriter(new FileWriter(reader.readLine()));
        while (true){
            String s = reader.readLine();
            out.write(s+"\n");
            if(s.equals("exit")) break;
        }

        out.close();
        reader.close();

    }
}
