package com.javarush.task.task38.task3802;

/* 
Проверяемые исключения (checked exception)
*/

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class VeryComplexClass {
    public void veryComplexMethod() throws Exception {
        Path file = Paths.get("\\\\");
        Files.lines(file);
    }

    public static void main(String[] args) throws Exception {
        new VeryComplexClass().veryComplexMethod();
    }
}
