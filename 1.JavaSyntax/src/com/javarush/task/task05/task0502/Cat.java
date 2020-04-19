package com.javarush.task.task05.task0502;

/* 
Реализовать метод fight
*/

public class Cat {
    public int age;
    public int weight;
    public int strength;

    public Cat() {
    }

    public boolean fight(Cat anotherCat) {
        int kriteria1 = 0, kriteria2 = 0;
        if (age > anotherCat.age) kriteria1++;
        else if (age < anotherCat.age) kriteria2++;
        if (weight > anotherCat.weight) kriteria1++;
        else if (weight < anotherCat.weight) kriteria2++;
         if(strength > anotherCat.strength) kriteria1++;
         else if(strength < anotherCat.strength) kriteria2++;

         return kriteria1 > kriteria2;
    }

    public static void main(String[] args) {
 /*       Cat cat1 = new Cat();
        cat1.age = 1;
        cat1.weight = 2;
        cat1.strength = 2;

        Cat cat2 = new Cat();
        cat2.age = 4;
        cat2.weight = 3;
        cat2.strength = 3;

        System.out.println(cat1.fight(cat2));
        System.out.println(cat2.fight(cat1));
*/
    }
}
