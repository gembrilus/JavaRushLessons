package com.javarush.task.task19.task1918;

/* 
Знакомство с тегами
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution {

    private static List<String> match(String tag, Pattern p, String s){
        List<String> res = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        Matcher m = p.matcher(s);
        int count = 0;
        while (m.find()) {
            if (m.group().startsWith("<" + tag)) {
                indexes.add(m.start());
                count++;
            } else {
                indexes.add(m.end());
                count--;
            }
            if(count == 0) {
                for (int i = 0; i < indexes.size()/2; i++)
                    res.add(s.substring(indexes.get(i), indexes.get(indexes.size()-i-1)));
                indexes.clear();
        }
        }
        return res;
    }

    public static void main(String[] args) {

        if(args.length != 1) return;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = "";

        try {
            file = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        StringBuilder html = new StringBuilder();
        Pattern pattern = Pattern.compile("</?" + args[0] + "[^>]*>");

        try(FileReader fileReader = new FileReader(file)){
            while (fileReader.ready()) html.append((char) fileReader.read());

            List<String> tegs = match(args[0], pattern, html.toString());
            for (String s: tegs) System.out.println(s);

        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
