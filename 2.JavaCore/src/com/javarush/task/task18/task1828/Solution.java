package com.javarush.task.task18.task1828;

/* 
Прайсы 2
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws IOException {
        if(args.length != 2 && args.length != 5) return;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String fileName = reader.readLine();
        String result = "";
        String id = String.format("%-8.8s", args[1]);

        List<String> strings = new ArrayList<>();
        try(FileReader in = new FileReader(fileName);
            BufferedReader fileReader = new BufferedReader(in)) {
                while (fileReader.ready()) strings.add(fileReader.readLine());
        }

        switch (args[0]) {
            case "-d":
                       Iterator<String> it = strings.iterator();
                       while (it.hasNext())
                           if(it.next().startsWith(id)) it.remove();
                       break;
            case "-u":
                       result = id + String.format("%-30.30s", args[2]) +
                                            String.format("%-8.8s", args[3]) +
                                            String.format("%-4.4s", args[4]);
                       for(int i = 0; i < strings.size(); i++)
                            if(strings.get(i).startsWith(id)) strings.set(i, result);
                       break;
            default: return;
        }

        try(FileOutputStream out = new FileOutputStream(fileName);
            BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(out))){
            for (String s: strings)
                fileWriter.append(s).append("\n");
        }
    }
}
