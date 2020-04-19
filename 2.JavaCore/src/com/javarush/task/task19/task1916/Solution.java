package com.javarush.task.task19.task1916;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* 
Отслеживаем изменения
*/

public class Solution {
    public static List<LineItem> lines = new ArrayList<LineItem>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file1 = "";
        String file2 = "";

        try {
            file1 = reader.readLine();
            file2 = reader.readLine();
        } catch (IOException e) {
            System.out.println("Невозможно считать строку");
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.out.println("Ошибка закрытия ридера");
                e.printStackTrace();
            }
        }

        try(BufferedReader fileReader1 = new BufferedReader(new FileReader(file1));
            BufferedReader fileReader2 = new BufferedReader(new FileReader(file2))){

            String temp = "";
            String temp2 = "";
            while (fileReader1.ready() || fileReader2.ready()){
                String s1 = temp.equals("") ? fileReader1.readLine() : temp;
                String s2 = temp2.equals("") ? fileReader2.readLine() : temp2;
                    if (s1.equals(s2)) lines.add(new LineItem(Type.SAME, s1));
                    else if (!s1.equals(s2)) {
                        String s11 = fileReader1.readLine();
                        String s12 = fileReader2.readLine();
                        if (s11.equals(s2)) {
                            lines.add(new LineItem(Type.REMOVED, s1));
                            lines.add(new LineItem(Type.SAME, s2));
                            temp = "";
                            temp2 = s12;
                        } else {
                            lines.add(new LineItem(Type.ADDED, s2));
                            lines.add(new LineItem(Type.SAME, s1));
                            temp = s11;
                            temp2 = "";
                        }
                    }
            }
            if(!temp2.equals("")) lines.add(new LineItem(Type.ADDED, temp2));
            if(!temp.equals("")) lines.add(new LineItem(Type.REMOVED, temp));

        } catch (IOException e){
            System.out.println("Невозможно открыть файл либо файл не найден");
            e.printStackTrace();
        }
    }


    public static enum Type {
        ADDED,        //добавлена новая строка
        REMOVED,      //удалена строка
        SAME          //без изменений
    }

    public static class LineItem {
        public Type type;
        public String line;

        public LineItem(Type type, String line) {
            this.type = type;
            this.line = line;
        }
    }
}
