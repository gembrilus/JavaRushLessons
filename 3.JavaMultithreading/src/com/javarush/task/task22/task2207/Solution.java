package com.javarush.task.task22.task2207;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* 
Обращенные слова
*/
public class Solution {
    public static List<Pair> result = new LinkedList<>();

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String file = null;
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
        if(file == null) return;
        try(BufferedReader freader = new BufferedReader(new FileReader(file))){
            int c = 0;
            StringBuilder s = new StringBuilder();
            List<String> words = new ArrayList<>();
            while((c = freader.read()) != -1){
                if(c == 10) c = 32;
                if(c == 32){
                    String s0 = s.toString();
                    String sReverse = new StringBuilder(s0)
                            .reverse()
                            .toString();

                    if(words.contains(sReverse)) {
                        Pair pair = new Pair();
                        pair.first = sReverse;
                        pair.second = s0;
                        if(!result.contains(pair)){
                            result.add(pair);
                        }
                        words.remove(sReverse);
                    } else {
                        words.add(0, s0);
                    }
                    s = new StringBuilder();
                } else s.append((char)c);
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static class Pair {
        String first;
        String second;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Pair pair = (Pair) o;

            if (first != null ? !first.equals(pair.first) : pair.first != null) return false;
            return second != null ? second.equals(pair.second) : pair.second == null;

        }

        @Override
        public int hashCode() {
            int result = first != null ? first.hashCode() : 0;
            result = 31 * result + (second != null ? second.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return  first == null && second == null ? "" :
                        first == null ? second :
                            second == null ? first :
                                first.compareTo(second) < 0 ? first + " " + second : second + " " + first;

        }
    }

}
