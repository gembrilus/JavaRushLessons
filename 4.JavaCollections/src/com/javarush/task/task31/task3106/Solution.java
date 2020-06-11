package com.javarush.task.task31.task3106;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

/*
Разархивируем файл
*/
public class Solution {
    public static void main(String[] args) throws IOException {

        List<String> argss = Arrays.stream(args, 1, args.length)
                .sorted(Comparator.comparingInt(arg -> Integer.parseInt(arg.substring(arg.length()-3))))
                .collect(Collectors.toList());
        List<InputStream> streams = new ArrayList<>();

        for (String arg: argss){
            streams.add(new FileInputStream(arg));
        }

        try(ZipInputStream zipInputStream = new ZipInputStream(new SequenceInputStream(Collections.enumeration(streams)));
        OutputStream outputStream = new FileOutputStream(args[0])) {
            while (zipInputStream.getNextEntry() != null) {
                byte[] buffer = new byte[1024 * 1024];
                int len;
                while ((len = zipInputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, len);
                }
                zipInputStream.closeEntry();
            }
        }
    }
}
