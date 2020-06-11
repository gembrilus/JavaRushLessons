package com.javarush.task.task33.task3310.strategy;

import com.javarush.task.task33.task3310.ExceptionHandler;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileBucket {
    private Path path;

    public FileBucket() {
        try {
            this.path = Files.createTempFile("temp-", null);
            Files.deleteIfExists(path);
            Files.createFile(path);
        } catch (Exception ignore){
        } finally {
            if (path != null) {
                path.toFile().deleteOnExit();
            }
        }
    }

    public long getFileSize(){
        long size = 0;
        try {
            size = Files.size(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
        return size;
    }

    public void putEntry(Entry entry){
        try(OutputStream os = Files.newOutputStream(path);
            ObjectOutputStream output = new ObjectOutputStream(os)){
            output.writeObject(entry);
        } catch (IOException e){
            ExceptionHandler.log(e);
        }
    }

    public Entry getEntry(){
        Entry entry = null;
        if (getFileSize() == 0) return null;
        try(InputStream is = Files.newInputStream(path);
            ObjectInputStream stream = new ObjectInputStream(is)){
            entry = (Entry) stream.readObject();
        } catch (IOException | ClassNotFoundException e){
            ExceptionHandler.log(e);
        }
        return entry;
    }

    public void remove(){
        try {
            Files.delete(path);
        } catch (IOException e) {
            ExceptionHandler.log(e);
        }
    }
}
