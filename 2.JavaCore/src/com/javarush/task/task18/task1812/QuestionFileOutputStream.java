package com.javarush.task.task18.task1812;

import java.io.*;

/* 
Расширяем AmigoOutputStream
*/

public class QuestionFileOutputStream implements AmigoOutputStream {
    private AmigoOutputStream amigoStream;

    public QuestionFileOutputStream(AmigoOutputStream stream) {
        amigoStream = stream;
    }

    @Override
    public void flush() throws IOException {
        amigoStream.flush();
    }

    @Override
    public void write(int b) throws IOException {
        amigoStream.write(b);
    }

    @Override
    public void write(byte[] b) throws IOException {
        amigoStream.write(b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        amigoStream.write(b, off, len);
    }

    @Override
    public void close() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Вы действительно хотите закрыть поток? Д/Н");
        if(reader.readLine().equals("Д")) amigoStream.close();
    }
}

