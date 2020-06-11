package com.javarush.task.task31.task3112;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;

/* 
Загрузчик файлов
*/
public class Solution {

    public static void main(String[] args) throws IOException {
        Path passwords = downloadFile("https://javarush.ru/testdata/secretPasswords.txt", Paths.get("/home/maksim"/*"D:/MyDownloads"*/));

        for (String line : Files.readAllLines(passwords)) {
            System.out.println(line);
        }
    }

    public static Path downloadFile(String urlString, Path downloadDirectory) throws IOException {
        URL url = new URL(urlString);
        String fileName = Paths.get(url.getFile()).getFileName().toString();
        Path outputFile = downloadDirectory.resolve(fileName);
        Path tempFile;

        try (InputStream stream = url.openStream()) {
            tempFile = Files.createTempFile("temp-", ".tmp");
            Files.copy(stream, tempFile, StandardCopyOption.REPLACE_EXISTING);
        }

        Files.move(tempFile, outputFile, StandardCopyOption.REPLACE_EXISTING);
        return outputFile;
    }
}
