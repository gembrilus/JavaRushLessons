package com.javarush.task.task31.task3105;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/* 
Добавление файла в архив
*/
public class Solution {
    public static void main(String[] args) throws IOException {
        Path fileName = Paths.get(args[0]);
        String fileNameOnly = fileName.getFileName().toString();
        Path zip = Paths.get(args[1]);

        Map<String, byte[]> map = new HashMap<>();
        try (ZipInputStream zipInputStream = new ZipInputStream(Files.newInputStream(zip))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();

                byte[] buffer = new byte[8 * 1024];
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    bos.write(buffer, 0, len);
                }
                map.put(entry.getName(), bos.toByteArray());
                zipInputStream.closeEntry();
            }
        }

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zip))) {
            boolean isExist = false;
            for (Map.Entry<String, byte[]> pair : map.entrySet()) {
                String name = pair.getKey();
                zipOutputStream.putNextEntry(new ZipEntry(name));

                if (name.endsWith(fileNameOnly)) {
                    isExist = true;
                    Files.copy(fileName, zipOutputStream);
                } else {
                    zipOutputStream.write(pair.getValue());
                }

                zipOutputStream.closeEntry();
            }

            if (!isExist) {
                ZipEntry newEntry = new ZipEntry("new/" + fileNameOnly);
                zipOutputStream.putNextEntry(newEntry);
                Files.copy(fileName, zipOutputStream);
                zipOutputStream.closeEntry();
            }
        }
    }
}
