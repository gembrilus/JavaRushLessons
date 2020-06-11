package com.javarush.task.task31.task3111;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class SearchFileVisitor extends SimpleFileVisitor<Path> {

    private String partOfName;
    private String partOfContent;
    private int minSize = Integer.MAX_VALUE, maxSize = 0;

    private List<Path> foundFiles = new ArrayList<>();

    public List<Path> getFoundFiles() {
        return foundFiles;
    }

    public void setPartOfName(String partOfName) {
        this.partOfName = partOfName;
    }

    public void setPartOfContent(String partOfContent) {
        this.partOfContent = partOfContent;
    }

    public void setMinSize(int minSize) {
        this.minSize = minSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        long fileSize = Files.size(file);
        int conditions = 0;
        int evals = 0;

        boolean isPartOfName = partOfName != null;
        boolean isPartOfContent = partOfContent != null;
        boolean isMinSize = minSize < Integer.MAX_VALUE;
        boolean isMaxSize = maxSize > 0;

        conditions = isPartOfName ? conditions|1 : conditions;
        conditions = isPartOfContent ? conditions|(1<<1) : conditions<<1;
        conditions = isMinSize ? conditions|(1<<2) : conditions<<1;
        conditions = isMaxSize ? conditions|(1<<3) : conditions<<1;


        evals = isPartOfName && file.getFileName().toString().contains(partOfName) ? evals | 1 : evals;
        evals = isPartOfContent && Files.readAllLines(file, Charset.defaultCharset()).stream()
                .anyMatch(str -> str.contains(partOfContent)) ? evals | (1 << 1) : evals << 1;
        evals = isMinSize && fileSize > minSize ? evals | (1 << 2) : evals << 1;
        evals = isMaxSize && fileSize < maxSize ? evals | (1 << 3) : evals << 1;

        if (conditions == evals) foundFiles.add(file);

        return super.visitFile(file, attrs);
    }
}
