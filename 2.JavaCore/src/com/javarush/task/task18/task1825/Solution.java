package com.javarush.task.task18.task1825;

/*
Собираем файл
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        List<String> files = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String fileName = reader.readLine();
            if(fileName.equals("end")) break;
            files.add(fileName);
        }
        Collections.sort(files);
        String wholeFileName = files.get(0).replaceAll("\\.part\\d+$", "");
        for (String file: files){
            try(BufferedInputStream in = new BufferedInputStream(new FileInputStream(file));
                BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(wholeFileName, true))){
                while (in.available()>0) out.write(in.read());
            }
        }
    }
}
