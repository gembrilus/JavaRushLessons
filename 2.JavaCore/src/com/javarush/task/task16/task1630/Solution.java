package com.javarush.task.task16.task1630;

import java.io.*;

public class Solution {
    public static String firstFileName;
    public static String secondFileName;

    static {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            firstFileName = reader.readLine();
            secondFileName = reader.readLine();
        } catch (IOException e) { }
    }

    public static void main(String[] args) throws InterruptedException {
        systemOutPrintln(firstFileName);
        systemOutPrintln(secondFileName);
    }

    public static void systemOutPrintln(String fileName) throws InterruptedException {
        ReadFileInterface f = new ReadFileThread();
        f.setFileName(fileName);
        f.start();
        f.join();
        System.out.println(f.getFileContent());
    }

    public interface ReadFileInterface{

        void setFileName(String fullFileName);

        String getFileContent();

        void join() throws InterruptedException;

        void start();
    }

    public static class ReadFileThread extends Thread implements ReadFileInterface {
        private String filename;
        private StringBuilder content = new StringBuilder();

        @Override
        public void setFileName(String fullFileName) { filename = fullFileName; }

        @Override
        public String getFileContent() { return content.toString(); }

        @Override
        public void run() {
            try(BufferedReader fileReader = new BufferedReader(new FileReader(filename));) {
                content.append(fileReader.lines().reduce("", (acc, str) -> acc += (str + " ")));
            } catch (IOException e){ }
        }
    }
}
