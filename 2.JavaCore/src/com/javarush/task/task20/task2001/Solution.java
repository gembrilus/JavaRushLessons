package com.javarush.task.task20.task2001;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* 
Читаем и пишем в файл: Human
*/
public class Solution {
    public static void main(String[] args) {
        //исправьте outputStream/inputStream в соответствии с путем к вашему реальному файлу
        try {
            File your_file_name = File.createTempFile("person", null, new File("/home/maksim/1"));
            OutputStream outputStream = new FileOutputStream(your_file_name);
            InputStream inputStream = new FileInputStream(your_file_name);

//            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
//            Human ivanov = new Human();
//            Human ivanov = new Human(null, new Asset("home", 999_999.99), new Asset("car", 2999.99));
//            Human ivanov = new Human("", new Asset("", 999_999.99), new Asset("", 2999.99));
            Human ivanov = new Human("Виктор Иванович", new Asset("", 0), new Asset(null, -1));
//            Human ivanov = new Human("Ivanov", new Asset("home", 999_999.99), new Asset("car", 2999.99));
            ivanov.save(outputStream);
            outputStream.flush();

            Human somePerson = new Human();
            somePerson.load(inputStream);
            inputStream.close();

            System.out.println(somePerson.toString());
            System.out.println(ivanov.toString());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with my file");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Oops, something wrong with save/load method");
        }
    }

    public static class Human {
        public String name;
        public List<Asset> assets = new ArrayList<>();

        public Human() {
        }

        public Human(String name, Asset... assets) {
            this.name = name;
            if (assets != null) {
                this.assets.addAll(Arrays.asList(assets));
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Human human = (Human) o;

            if (name != null ? !name.equals(human.name) : human.name != null) return false;
            return assets != null ? assets.equals(human.assets) : human.assets == null;
        }

        @Override
        public int hashCode() {
            int result = name != null ? name.hashCode() : 0;
            result = 31 * result + (assets != null ? assets.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "Human{" +
                    "name='" + name + '\'' +
                    ", assets=" + assets +
                    '}';
        }

        public void save(OutputStream outputStream) throws Exception {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));
            String h_name = name == null ? "null" : name;
            writer.write(h_name + "\n");
            if(assets.isEmpty()) writer.write("null\n");
            else {
                writer.write("asset\n");
                for (Asset a: assets){
                    String a_name = a.getName() != null ? a.getName() : "null";
                    double a_price = a.getPrice();
                    writer.write(a_name + "\n");
                    writer.write(Double.toString(a_price) + "\n");
                }
            }
            writer.close();
        }

        public void load(InputStream inputStream) throws Exception {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String h_name = reader.readLine();
            name = h_name.equals("null") ? null : h_name;
            String isAsset = reader.readLine();
            if(isAsset.equals("asset")){
                do {
                    String a_name = reader.readLine();
                    double a_price = Double.parseDouble(reader.readLine());
                    assets.add(new Asset(a_name.equals("null") ? null : a_name, a_price));
                } while (reader.ready());
            }
        }
    }
}
