package com.javarush.task.task20.task2013;

import java.io.*;
import java.util.List;

/* 
Externalizable Person
*/
public class Solution {
    public static class Person implements Externalizable {
        private String firstName;
        private String lastName;
        private int age;
        private Person mother;
        private Person father;
        private List<Person> children;

        public Person(){}

        public Person(String firstName, String lastName, int age) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.age = age;
        }

        public void setMother(Person mother) {
            this.mother = mother;
        }

        public void setFather(Person father) {
            this.father = father;
        }

        public void setChildren(List<Person> children) {
            this.children = children;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject(firstName);
            out.writeObject(lastName);
            out.writeInt(age);
            out.writeObject(mother);
            out.writeObject(father);
            out.writeObject(children);
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            firstName = (String)in.readObject();
            lastName = (String) in.readObject();
            age = in.readInt();
            mother = (Person)in.readObject();
            father = (Person)in.readObject();
            children = (List)in.readObject();
        }
    }

    public static void main(String[] args) throws IOException {

//        File file = File.createTempFile("test", null, new File("/home/maksim/1/"));
//
//        FileOutputStream fout = new FileOutputStream(file);
//        ObjectOutputStream out = new ObjectOutputStream(fout);
//
//        Person person = new Person("Макс", "Наконечный", 36);
//        person.setMother(new Person("Ольга", "Даниловна", 66));
//        person.setFather(new Person("Леонид","Осипов", 55));
//        person.setChildren(List.of(new Person("Анна", "Наконечная", 1), new Person("Стелла", "Наконечная", 1)));
//
//        out.writeObject(person);
//        out.flush();
//
//        FileInputStream fin = new FileInputStream(file);
//        ObjectInputStream in = new ObjectInputStream(fin);
//        Person loadedPerson = null;
//        try {
//            loadedPerson = (Person) in.readObject();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        in.close();
//
//        System.out.println(loadedPerson.firstName + " " + loadedPerson.lastName + " " + loadedPerson.age + " лет \n" +
//                loadedPerson.mother + "\n" +
//                loadedPerson.father + "\n");
//        for(Person child: loadedPerson.children)
//            System.out.println(child);


    }
}
