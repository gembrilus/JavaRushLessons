package com.javarush.task.task05.task0526;

/* 
Мужчина и женщина
*/

public class Solution {
    public static void main(String[] args) {

        Man man1 = new Man("Max", 36, "Ukraine, Cherkassy");
        Man man2 = new Man("Andrey", 32, "Ukraine, Kiev");

        Woman woman1 = new Woman("Tanya", 30, "Unknown");
        Woman woman2 = new Woman("Krista", 25, "20091 Kiev, Shevchenko st. 15-181");

        System.out.println(man1);
        System.out.println(man2);
        System.out.println(woman1);
        System.out.println(woman2);

    }

    public static class Man{
        private String name;
        private int age;
        private String address;

        public Man(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }


        public String toString(){return (name + " " + age + " " + address);}

    }

    public static class Woman{
        private String name;
        private int age;
        private String address;

        public Woman(String name, int age, String address){
            this.name = name;
            this.age = age;
            this.address = address;
        }

        public String toString(){return (name + " " + age + " " + address);}
    }
}
