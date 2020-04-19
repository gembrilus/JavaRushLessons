package com.javarush.task.task19.task1917;

/* 
Свой FileWriter
*/

import java.io.*;

public class FileConsoleWriter {
    private FileWriter fileWriter;

    public FileConsoleWriter(String fileName) throws IOException {
        fileWriter = new FileWriter(fileName);
    }

    public FileConsoleWriter(String fileName, boolean append) throws IOException {
        fileWriter = new FileWriter(fileName, append);
    }

    public FileConsoleWriter(File file) throws IOException {
        fileWriter = new FileWriter(file);
    }

    public FileConsoleWriter(File file, boolean append) throws IOException {
        fileWriter = new FileWriter(file, append);
    }

    public FileConsoleWriter(FileDescriptor fd) throws IOException {
        fileWriter = new FileWriter(fd);
    }

    public void write(char[] cbuf, int off, int len) throws IOException{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        fileWriter.write(cbuf, off, len);
        out.write(cbuf, off, len);
        out.close();

    }
    public void write(int c) throws IOException{
        fileWriter.write(c);
        System.out.println(c);
    }
    public void write(String str) throws IOException{
        fileWriter.write(str);
        System.out.println(str);
    }
    public void write(String str, int off, int len) throws IOException {
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        fileWriter.write(str, off, len);
        out.write(str, off, len);
        out.close();
    }
    public void write(char[] cbuf) throws IOException{
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
        fileWriter.write(cbuf);
        out.write(cbuf);
        out.close();
    }
    public void close() throws IOException{
        fileWriter.close();
    }


    public static void main(String[] args) {

//        try {
//            FileConsoleWriter writer = new FileConsoleWriter("/home/maksim/1/writerTest");
//            writer.write(123);
//            writer.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

}
