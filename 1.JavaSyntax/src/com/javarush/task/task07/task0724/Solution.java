package com.javarush.task.task07.task0724;

/* 
Семейная перепись
*/

public class Solution {
    public static void main(String[] args) {
        Human gfather1 = new Human("Ded", true, 90);
        Human gfather2 = new Human("Hrych", true, 85);

        Human gmother1 = new Human("Baba", false, 82);
        Human gmother2 = new Human("Zina", false, 83);

        Human father = new Human("Papik", true, 38, gfather1, gmother1);
        Human mother = new Human("Mamik", false, 30, gfather2, gmother2);

        Human kind1 = new Human("Sonya", false, 8, father, mother);
        Human kind2 = new Human("Lika", false, 5, father, mother);
        Human kind3 = new Human("Siala", false, 2, father, mother);

        System.out.println(gfather1);
        System.out.println(gfather2);
        System.out.println(gmother1);
        System.out.println(gmother2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(kind1);
        System.out.println(kind2);
        System.out.println(kind3);
    }

    public static class Human {
        private String name;
        private boolean sex;
        private int age;
        private Human father;
        private Human mother;


        public Human(String name, boolean sex, int age){
            this.name = name;
            this.sex = sex;
            this.age = age;
        }


        public Human(String name, boolean sex, int age, Human father, Human mother){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.father = father;
            this.mother = mother;
        }


        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            if (this.father != null) {
                text += ", отец: " + this.father.name;
            }

            if (this.mother != null) {
                text += ", мать: " + this.mother.name;
            }

            return text;
        }
    }
}