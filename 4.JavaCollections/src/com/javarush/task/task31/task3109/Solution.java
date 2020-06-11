package com.javarush.task.task31.task3109;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Files;
import java.util.Properties;

/* 
Читаем конфиги
*/
public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Properties properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.xml");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/properties.txt");
        properties.list(System.out);

        properties = solution.getProperties("4.JavaCollections/src/com/javarush/task/task31/task3109/notExists");
        properties.list(System.out);
    }

    public Properties getProperties(String fileName) {
        File file = new File(fileName);
        Properties properties = new Properties();
        try {
            if (file.getName().endsWith(".txt")){
                properties.load(new FileReader(file));
            } else if(file.getName().endsWith(".xml")){
                properties.loadFromXML(Files.newInputStream(file.toPath()));
            } else {
                properties.load(Files.newInputStream(file.toPath()));
            }
            return properties;
        } catch (Exception ignore){
            return properties;
        }
    }
}
