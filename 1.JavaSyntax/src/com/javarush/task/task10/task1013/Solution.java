package com.javarush.task.task10.task1013;

/* 
Конструкторы класса Human
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static class Human {
        private String name;
        private int age;
        private boolean sex;
        private String address;
        private String number;
        private Human[] parents;

        public Human() {
        }

        public Human(String name) {
            this.name = name;
        }

        public Human(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public Human(String name, String number) {
            this.name = name;
            this.number = number;
        }

        public Human(int age, boolean sex) {
            this.age = age;
            this.sex = sex;
        }

        public Human(String name, int age, boolean sex) {
            this(name, age);
            this.sex = sex;
        }

        public Human(String name, int age, boolean sex, String address) {
            this(name, age, sex);
            this.address = address;
        }

        public Human(String name, int age, boolean sex, String address, String number) {
            this(name, age, sex, address);
            this.number = number;
        }

        public Human(String name, int age, boolean sex, String address, String number, Human[] parents) {
            this(name, age, sex, address, number);
            this.parents = parents;
        }

        public Human(Human human) {
            this.name = human.name;
            this.age = human.age;
            this.sex = human.sex;
            this.address = human.address;
            this.number = human.number;
            this.parents = human.parents;
        }
    }
}
