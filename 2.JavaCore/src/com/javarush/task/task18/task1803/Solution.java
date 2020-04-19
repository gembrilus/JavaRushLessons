package com.javarush.task.task18.task1803;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Самые частые байты
*/

public class Solution {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();
        FileInputStream file = new FileInputStream(fileName);

        Map<Integer, Integer> res = new HashMap<>();

        while (file.available() > 0){
            int key = file.read();
            if(res.containsKey(key)) res.put(key, res.get(key)+1);
            else res.put(key, 1);
        }
        file.close();

        int max = Collections.max(res.values());
        for (Map.Entry<Integer, Integer> it : res.entrySet())
            if(it.getValue() == max) System.out.print(it.getKey() + " ");
    }
}
