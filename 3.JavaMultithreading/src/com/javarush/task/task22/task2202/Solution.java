package com.javarush.task.task22.task2202;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
Найти подстроку
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getPartOfString("JavaRush - лучший сервис обучения Java."));
    }

    public static String getPartOfString(String string) {
        Pattern p = Pattern.compile("(?:^[^ ]+ )(\\S+ \\S+ \\S+ \\S+)");
        try{
            Matcher m = p.matcher(string);
            if(m.find()) return m.group(1);
            else throw new TooShortStringException();
        } catch (Exception e) {
            throw new TooShortStringException();
        }
    }

    public static class TooShortStringException extends RuntimeException {
    }
}
