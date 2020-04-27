package com.javarush.task.task32.task3209;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class HTMLFileFilter extends FileFilter {

    @Override
    public boolean accept(File f) {
        String suffix = f.getName().replaceFirst("^\\S+\\.+", "");
        if (
                f.isDirectory() ||
                        suffix.equalsIgnoreCase("html") ||
                        suffix.equalsIgnoreCase("htm")
        ) {
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "HTML и HTM файлы";
    }
}
