package com.javarush.task.task19.task1921;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* 
Хуан Хуанович
*/

public class Solution {
    public static final List<Person> PEOPLE = new ArrayList<Person>();

    public static void main(String[] args) {
        if(args.length != 1) return;
        Pattern p = Pattern.compile("(.+) (\\d+ \\d+ \\d+)");
        try(FileReader io = new FileReader(args[0]);
            BufferedReader fileReader = new BufferedReader(io)){
            while (fileReader.ready()){
                Matcher m = p.matcher(fileReader.readLine());
                m.find();
                String name = m.group(1);
                Date date = new SimpleDateFormat("dd MM yyyy").parse(m.group(2));
                PEOPLE.add(new Person(name, date));
            }
        } catch (IOException | ParseException e) { e.printStackTrace(); }
    }
}
