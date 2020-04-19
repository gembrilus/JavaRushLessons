package com.javarush.task.task18.task1827;

/* 
Прайсы
*/

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public static void main(String[] args) throws Exception {
        if(args.length < 4) return;

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String fileName = reader.readLine();

        if(args[0].equals("-c")){
            String productName = String.format("%-30.30s", args[1]);
            String price = String.format("%-8.8s", args[2]);
            String quantity = String.format("%-4.4s", args[3]);

            String lastString = "";
            List<Integer> ids = new ArrayList<>();
            try(FileInputStream in = new FileInputStream(fileName);
                BufferedReader fileReader = new BufferedReader(new InputStreamReader(in))){
                while (fileReader.ready()) {
                    lastString = fileReader.readLine();
                    ids.add(Integer.valueOf(lastString.substring(0, 8).trim()));
                }
            }

            int _id = Collections.max(ids) + 1;
            String id = String.format("%-8.8s",_id);
            String result = "\n" + id + productName + price + quantity;

            try(FileOutputStream out = new FileOutputStream(fileName,true);
                BufferedWriter fileWriter = new BufferedWriter(new OutputStreamWriter(out))){
                fileWriter.write(result);
            }
        }
    }
}
