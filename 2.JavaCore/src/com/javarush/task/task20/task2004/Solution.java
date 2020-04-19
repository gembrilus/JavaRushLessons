package com.javarush.task.task20.task2004;

import java.io.*;

/* 
Читаем и пишем в файл статики
*/
public class Solution {
    public static void main(String[] args) {
        //you can find your_file_name.tmp in your TMP directory or adjust outputStream/inputStream according to your file's actual location
        //вы можете найти your_file_name.tmp в папке TMP или исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {

            File yourFile = File.createTempFile("static", null, new File("/home/maksim/1/"));
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            ClassWithStatic classWithStatic = new ClassWithStatic();
            classWithStatic.i = 3;
            classWithStatic.j = 4;
            classWithStatic.save(outputStream);
            outputStream.flush();

            ClassWithStatic loadedObject = new ClassWithStatic();
            loadedObject.staticString = "something";
            loadedObject.i = 6;
            loadedObject.j = 7;

            loadedObject.load(inputStream);
            System.out.println(loadedObject);

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class ClassWithStatic {
        public static String staticString = "This is a static test string";
        public int i;
        public int j;

        public void save(OutputStream outputStream) throws Exception {
            String static_str = staticString == null ? "null" : staticString;
            outputStream.write((staticString + "\n" + i + "\n" + j).getBytes());
            outputStream.write(10);
            outputStream.close();
        }

        public void load(InputStream inputStream) throws Exception {
            StringBuilder s = new StringBuilder();
            int k = 0;
            while (inputStream.available()>0){
                int b = inputStream.read();
                if(b != 10) s.append((char) b);
                else{
                    switch (++k){
                        case 1:     staticString = s.toString().equals("null") ? null : s.toString(); s = new StringBuilder(); break;
                        case 2:     i = Integer.valueOf(s.toString()); s = new StringBuilder(); break;
                        case 3:     j = Integer.valueOf(s.toString()); s = new StringBuilder(); break;
                    }
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            ClassWithStatic that = (ClassWithStatic) o;

            if (i != that.i) return false;
            return j == that.j;

        }

        @Override
        public int hashCode() {
            int result = i;
            result = 31 * result + j;
            return result;
        }

        @Override
        public String toString() {
            return "ClassWithStatic{" +
                    "staticString=" + staticString +
                    ", i=" + i +
                    ", j=" + j +
                    '}';
        }
    }
}
