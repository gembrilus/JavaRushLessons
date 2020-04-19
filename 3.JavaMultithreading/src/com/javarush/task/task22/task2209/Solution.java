package com.javarush.task.task22.task2209;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/*
Составить цепочку слов
*/
public class Solution {
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
        if (file == null) return;
        List<String> list = new ArrayList<>();
        try (BufferedReader freader = new BufferedReader(new FileReader(file))) {
            list = freader.lines()
                    .flatMap((String s) -> Arrays.stream(s.split(" ")))
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] words = new String[list.size()];
        list.toArray(words);

        StringBuilder result = getLine(words);
        System.out.println(result.toString());
    }

    public static StringBuilder getLine(String... words) {
        if (words.length == 0) return new StringBuilder();
        if (words.length == 1) return new StringBuilder(words[0]);

        class ChainCenerator {

            private String maxChain = "";

            private void buildChain(StringBuilder res, List<String> list) {
                for (int i = 0; i < list.size(); i++) {
                    String w = list.get(i);
                    char f = w.toLowerCase().charAt(0);
                    char l = w.toLowerCase().charAt(w.length() - 1);
                    if (res.toString().contains(w)) continue;
                    int removeMode = -1;
                    int index = res.length() - 1;
                    int index2 = res.toString().toLowerCase().indexOf(f + " " + l);
                    if (index2 != -1) {
                        removeMode = 0;
                        res.insert(index2 + 1, " " + w);
                    } else if (res.toString().toLowerCase().charAt(index) == f) {
                        removeMode = 1;
                        res.append(" ").append(w);
                    } else if (res.toString().toLowerCase().charAt(0) == l) {
                        removeMode = 2;
                        res = new StringBuilder(w).append(" ").append(res);
                    } else continue;

                    list.remove(w);
                    if (res.toString().split(" ").length > maxChain.split(" ").length) maxChain = res.toString();
                    buildChain(res, list);

                    switch (removeMode) {
                        case 0:
                            res.delete(index2 + 1, index2 + 1 + w.length());
                            break;
                        case 1:
                            res.delete(index + 1, res.length());
                            break;
                        case 2:
                            res.delete(0, w.length());
                            break;
                    }
                }
            }
        }

        ChainCenerator chain = new ChainCenerator();
        for (String s : words) chain.buildChain(new StringBuilder(s), new ArrayList<>(Arrays.asList(words)));
        return new StringBuilder(chain.maxChain);
    }
}