package com.javarush.task.task31.task3103;

import java.io.IOException;
import java.util.List;

/* 
Своя реализация
*/
public class Solution {
    public static byte[] readBytes(String fileName) throws IOException {
        return java.nio.file.Files.readAllBytes(
                java.nio.file.Paths.get(fileName)
        );
    }

    public static List<String> readLines(String fileName) throws IOException {
        return java.nio.file.Files.readAllLines(
                java.nio.file.Paths.get(fileName),
                java.nio.charset.Charset.defaultCharset()
        );
    }

    public static void writeBytes(String fileName, byte[] bytes) throws IOException {
        java.nio.file.Path name = java.nio.file.Paths.get(fileName);
        try(java.io.OutputStream out = java.nio.file.Files.newOutputStream(name)){
            out.write(bytes);
        }
    }

    public static void copy(String resourceFileName, String destinationFileName) throws IOException {
        java.nio.file.Path source = java.nio.file.Paths.get(resourceFileName);
        java.nio.file.Path target = java.nio.file.Paths.get(destinationFileName);
        java.nio.file.Files.copy(source, target);
    }
}
