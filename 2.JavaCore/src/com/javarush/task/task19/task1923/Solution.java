package com.javarush.task.task19.task1923;

/* 
Слова с цифрами
*/

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {
    public static void main(String[] args) {
        if(args.length != 2) return;
        Pattern p = Pattern.compile("\\S*\\d+\\S*");
        try(BufferedReader fileReader = new BufferedReader(new FileReader(args[0]));
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(args[1]))){
            while (fileReader.ready()){
                Matcher m = p.matcher(fileReader.readLine());
                while (m.find()) fileWriter.write(m.group() + " ");
                }
        }catch (IOException e){e.printStackTrace();}

    }
}
