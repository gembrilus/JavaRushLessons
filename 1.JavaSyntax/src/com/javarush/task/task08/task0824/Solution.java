package com.javarush.task.task08.task0824;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* 
Собираем семейство
*/

public class Solution {
    public static void main(String[] args) {
        Human child1 = new Human("Маша", false, 2, new ArrayList<>(0));
        Human child2 = new Human("Маша", false, 4, new ArrayList<>(0));
        Human child3 = new Human("Маш", true, 6, new ArrayList<>(0));

        Human mother = new Human("Маня", false, 4, new ArrayList<>(Arrays.asList(child1, child2, child3)));
        Human father = new Human("Torion", true, 4, new ArrayList<>(Arrays.asList(child1, child2, child3)));

        Human gf1 = new Human("Viktor", true, 76, new ArrayList<>(Collections.singletonList(mother)));
        Human gf2 = new Human("Tor", true, 80, new ArrayList<>(Collections.singletonList(father)));

        Human gm1 = new Human("Vika", true, 76, new ArrayList<>(Collections.singletonList(mother)));
        Human gm2 = new Human("Vala", true, 70, new ArrayList<>(Collections.singletonList(father)));

        System.out.println(gf1);
        System.out.println(gf2);
        System.out.println(gm1);
        System.out.println(gm2);
        System.out.println(father);
        System.out.println(mother);
        System.out.println(child1);
        System.out.println(child2);
        System.out.println(child3);


    }

    public static class Human {
        String name;
        boolean sex;
        int age;
        ArrayList<Human> children;

        public Human(String name, boolean sex, int age, ArrayList<Human> children){
            this.name = name;
            this.sex = sex;
            this.age = age;
            this.children = children;
        }

        public String toString() {
            String text = "";
            text += "Имя: " + this.name;
            text += ", пол: " + (this.sex ? "мужской" : "женский");
            text += ", возраст: " + this.age;

            int childCount = this.children.size();
            if (childCount > 0) {
                text += ", дети: " + this.children.get(0).name;

                for (int i = 1; i < childCount; i++) {
                    Human child = this.children.get(i);
                    text += ", " + child.name;
                }
            }
            return text;
        }
    }
}
