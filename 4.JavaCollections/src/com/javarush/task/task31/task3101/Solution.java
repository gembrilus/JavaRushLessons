package com.javarush.task.task31.task3101;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/*
Проход по дереву файлов
*/
public class Solution {
    public static void main(String[] args) {
        File path = new File(args[0]);
        File resultFileAbsolutePath = new File(args[1]);

        File allFilesContent = new File(resultFileAbsolutePath.getParent() + "/allFilesContent.txt");
        FileUtils.renameFile(resultFileAbsolutePath, allFilesContent);

        List<File> files = new ArrayList<>();
        filesTree(path, files);
        files.sort(Comparator.comparing(File::getName));

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(allFilesContent))){
            for (File file: files){
                    try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        while (reader.ready()) {
                            writer.write(reader.read());
                        }
                        writer.write("\n");
                    }
            }
        } catch (Exception ignore) {
        }
    }

    private static void filesTree(File path, List<File> files) {
        File[] filesList = path.listFiles();
        if (filesList == null) return;
        for (File file : filesList) {
            if (file.isDirectory()) {
                filesTree(file, files);
            } else {
                if (file.length() <= 50) {
                    files.add(file);
                }
            }
        }
    }

}
