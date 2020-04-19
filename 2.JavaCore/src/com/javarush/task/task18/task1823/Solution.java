package com.javarush.task.task18.task1823;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* 
Нити и байты
*/

public class Solution {
    public static Map<String, Integer> resultMap = new HashMap<String, Integer>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        do{
            String file = reader.readLine();
            if(file.equals("exit")) break;
            resultMap.put(file, 0);
        } while (true);

        for (String file : resultMap.keySet())
            new ReadThread(file).start();

//        for (Map.Entry<String, Integer> pair: resultMap.entrySet())
//            System.out.println(pair.getKey() + " " + pair.getValue());
    }

    public static class ReadThread extends Thread {
        public ReadThread(String fileName) {
            super(fileName);
        }

        @Override
        public void run() {
            try (FileInputStream inputStream = new FileInputStream(getName())) {
                Map<Integer, Integer> map = new HashMap<>();
                while (inputStream.available()>0) {
                    int b = inputStream.read();
                    if(map.containsKey(b)) map.replace(b, map.get(b)+1);
                    else map.put(b, 1);
                }

                int max = Collections.max(map.values());

                for (Map.Entry<Integer, Integer> pair : map.entrySet()){
                    if(pair.getValue() == max) resultMap.replace(getName(), pair.getKey());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // implement file reading here - реализуйте чтение из файла тут
    }
}
