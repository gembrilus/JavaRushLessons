package com.javarush.task.task20.task2003;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/* 
Знакомство с properties
*/
public class Solution {
    public static Map<String, String> properties = new HashMap<>();

    public void fillInPropertiesMap() throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = reader.readLine();
        FileInputStream io = new FileInputStream(file);
        load(io);
    }

    public void save(OutputStream outputStream) throws IOException {
        Properties prop = new Properties();
        properties.forEach(prop::put);
        prop.store(outputStream, "");
    }

    public void load(InputStream inputStream) throws Exception {
        Properties prop = new Properties();
        prop.load(inputStream);
        prop.putAll(properties);
    }

    public static void main(String[] args) throws Exception {
        Solution solution = new Solution();
        FileOutputStream out = new FileOutputStream("/home/maksim/1/properties2");

        solution.fillInPropertiesMap();

        properties.forEach((key, val) -> System.out.println(key + " = " + val));

        solution.save(out);
    }
}