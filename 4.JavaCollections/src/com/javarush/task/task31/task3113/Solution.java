package com.javarush.task.task31.task3113;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/* 
Что внутри папки?
*/
public class Solution {

    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        Path dir = Paths.get(path);
        if (!Files.isDirectory(dir)){
            System.out.format("%s - не папка", dir);
            return;
        }
        System.out.format("Всего папок - %d%n", Files.walk(dir).filter(Files::isDirectory).count() - 1);
        System.out.format("Всего файлов - %d%n", Files.walk(dir).filter(Files::isRegularFile).count());
        System.out.format("Общий размер - %d%n", Files.walk(dir).filter(Files::isRegularFile).mapToLong(p -> {
            try {
                return Files.size(p);
            } catch (IOException ignore) {
            }
            return 0;
        }).sum());
    }
}
