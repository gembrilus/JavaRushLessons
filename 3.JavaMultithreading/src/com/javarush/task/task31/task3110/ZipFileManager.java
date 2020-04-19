package com.javarush.task.task31.task3110;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFileManager {
    private Path zipFile;

    public ZipFileManager(Path zipFile) {
        this.zipFile = zipFile;
    }

    public void createZip(Path source) throws Exception{

        try(OutputStream out = Files.newOutputStream(zipFile);
        ZipOutputStream zipOut = new ZipOutputStream(out)) {

            String fileName = source.getFileName().toString();
            ZipEntry zipEntry = new ZipEntry(fileName);
            zipOut.putNextEntry(zipEntry);

            try (InputStream in = Files.newInputStream(source)) {
                while (in.available() > 0){
                    zipOut.write(in.read());
                }
                zipOut.closeEntry();
            }
        }
    }
}
