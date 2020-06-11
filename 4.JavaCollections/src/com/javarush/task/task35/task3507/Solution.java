package com.javarush.task.task35.task3507;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/* 
ClassLoader - что это такое?
*/
public class Solution extends ClassLoader {
    public static void main(String[] args) {
        Set<? extends Animal> allAnimals = getAllAnimals(Solution.class.getProtectionDomain().getCodeSource().getLocation().getPath() + Solution.class.getPackage().getName().replaceAll("[.]", "/") + "/data");
        System.out.println(allAnimals);
    }

    public static Set<? extends Animal> getAllAnimals(String pathToAnimals) {
        Set<Animal> set = new HashSet<>();

        File dir = new File(pathToAnimals);
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptySet();
        }
        try {
            for (File file : files) {
                if (!file.getName().endsWith(".class")) continue;

                Class<?> clazz = new Solution().findClass(file.getAbsolutePath());
                for(Constructor<?> constructor: clazz.getConstructors()){
                    if (constructor.getParameterTypes().length == 0){
                        if (Animal.class.isAssignableFrom(clazz)){
                            set.add((Animal) constructor.newInstance());
                        }
                    }
                }
            }
        } catch (Exception ignore) {
        }
        return set;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
                try {
                    Path file = Paths.get(name);
                    byte[] bytes = Files.readAllBytes(file);
                    return defineClass(null, bytes, 0, bytes.length);
                } catch (IOException e) {
                    return super.findClass(name);
                }
    }
}
