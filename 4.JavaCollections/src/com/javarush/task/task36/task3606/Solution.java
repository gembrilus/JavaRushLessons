package com.javarush.task.task36.task3606;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/* 
Осваиваем ClassLoader и Reflection
*/
public class Solution extends ClassLoader {
    private List<Class> hiddenClasses = new ArrayList<>();
    private String packageName;

    public Solution(String packageName) {
        this.packageName = packageName;
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Solution solution = new Solution(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "com/javarush/task/task36/task3606/data/second");
        solution.scanFileSystem();
        System.out.println(solution.getHiddenClassObjectByKey("secondhiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("firsthiddenclassimpl"));
        System.out.println(solution.getHiddenClassObjectByKey("packa"));
    }

    public void scanFileSystem() throws ClassNotFoundException {
        try (Stream<Path> stream = Files.list(Paths.get(packageName))) {
            List<String> files = stream
                    .map(Path::toString)
                    .filter(s -> s.endsWith(".class"))
                    .collect(Collectors.toList());

            for (String file : files) {
                hiddenClasses.add(findClass(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public HiddenClass getHiddenClassObjectByKey(String key) {
        HiddenClass hiddenClass = null;
        try {
            for (Class<?> clazz : hiddenClasses) {
                if (clazz.getSimpleName().toLowerCase().startsWith(key.toLowerCase())) {
                    if (HiddenClass.class.isAssignableFrom(clazz)) {
                        Constructor[] constructors = clazz.getDeclaredConstructors();
                        for (Constructor c: constructors) {
                            if (c.getParameterCount() == 0) {
                                c.setAccessible(true);
                                hiddenClass = (HiddenClass) c.newInstance();
                            }
                        }
                    }
                }
            }
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return hiddenClass;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] bytes = Files.readAllBytes(Paths.get(name));
            return defineClass(null, bytes, 0, bytes.length);
        } catch (IOException e) {
            return super.findClass(name);
        }
    }
}

