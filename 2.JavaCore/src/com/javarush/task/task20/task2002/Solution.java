package com.javarush.task.task20.task2002;

import java.io.*;
import java.util.*;

/* 
Читаем и пишем в файл: JavaRush
*/
public class Solution {
    public static void main(String[] args) {
        try {
            File yourFile = File.createTempFile("users", null, new File("/home/maksim/1"));
            OutputStream outputStream = new FileOutputStream(yourFile);
            InputStream inputStream = new FileInputStream(yourFile);

            JavaRush javaRush = new JavaRush();

            //Создаем юзеров, добавляем их в список.
            User u1 = new User();
            u1.setFirstName("Вася");
            u1.setLastName("Смит");
            u1.setBirthDate(new GregorianCalendar(1983, 2, 22).getTime());
            u1.setMale(true);
            u1.setCountry(User.Country.RUSSIA);

            User u2 = new User();
            u2.setFirstName("");
            u2.setLastName("Smit");
            u2.setBirthDate(new Date(0L));
            u2.setMale(true);
            u2.setCountry(User.Country.OTHER);

            User u3 = new User();

            User u4 = new User();
            u4.setFirstName("");
            u4.setLastName("");
            u4.setBirthDate(null);
            u4.setCountry(null);

            Collections.addAll(javaRush.users, u1, u2, u3, u4);

            //сохраняем

            javaRush.save(outputStream);
            outputStream.flush();

            //загружаем

            JavaRush loadedObject = new JavaRush();
            loadedObject.load(inputStream);

            //Выводим созданный и загруженный объекты

            System.out.printf("%s%n%s%n%b", javaRush, loadedObject, javaRush.equals(loadedObject));

            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something is wrong with the save/load method");
        }
    }

    public static class JavaRush {
        public List<User> users = new ArrayList<>();

        public void save(OutputStream outputStream) throws Exception {
            if(!users.isEmpty()){
                for(User u: users){
                    if(u.getFirstName()!=null)
                        outputStream.write(u.getFirstName().getBytes());
                    else outputStream.write("null".getBytes());
                    outputStream.write(10);
                    if(u.getLastName() != null)
                        outputStream.write(u.getLastName().getBytes());
                    else outputStream.write("null".getBytes());
                    outputStream.write(10);
                    if(u.getBirthDate() != null)
                        outputStream.write(Long.toString(u.getBirthDate().getTime()).getBytes());
                    else outputStream.write("null".getBytes());
                    outputStream.write(10);
                    outputStream.write((u.isMale()? "true":"false").getBytes());
                    outputStream.write(10);
                    if(u.getCountry() != null)
                        outputStream.write(u.getCountry().getDisplayName().getBytes());
                    else outputStream.write("null".getBytes());
                    outputStream.write(10);
                }
            }
            outputStream.close();
          }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                while (reader.ready()) {
                    User u = new User();
                    String f_name = reader.readLine();
                    if (!f_name.equals("null")) u.setFirstName(f_name);
                    else u.setFirstName(null);

                    String l_name = reader.readLine();
                    if (!l_name.equals("null")) u.setLastName(l_name);
                    else u.setLastName(null);

                    String date = reader.readLine();
                    if (!date.equals("null")) u.setBirthDate(new Date(Long.parseLong(date)));
                    else u.setBirthDate(null);

                    Boolean isMale = Boolean.parseBoolean(reader.readLine());
                    u.setMale(isMale);

                    String country = reader.readLine();
                    if (!country.equals("null")) u.setCountry(User.Country.valueOf(country.toUpperCase()));
                    else u.setCountry(null);
                    users.add(u);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            JavaRush javaRush = (JavaRush) o;

            return users != null ? users.equals(javaRush.users) : javaRush.users == null;

        }

        @Override
        public int hashCode() {
            return users != null ? users.hashCode() : 0;
        }

        @Override
        public String toString() {
            StringBuilder s = new StringBuilder();
            int i = 1;
            for (User u : users) {
                       s.append("user").append(i).append("=[")
                        .append(u.getFirstName()).append(" ")
                        .append(u.getLastName()).append(" ")
                        .append(u.getBirthDate()).append(" ")
                        .append(u.isMale()).append(" ")
                        .append(u.getCountry()).append("]\n");
                       i++;
            }
            return s.toString();
        }
    }
}
