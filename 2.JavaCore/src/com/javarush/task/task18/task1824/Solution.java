package com.javarush.task.task18.task1824;

/* 
Файлы и исключения
*/

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file;
        while (true){
            file = reader.readLine();
            try(FileInputStream inputStream = new FileInputStream(file)){

            } catch (FileNotFoundException e){
                System.out.println(file);
                break;
            }
        }
    }
}
