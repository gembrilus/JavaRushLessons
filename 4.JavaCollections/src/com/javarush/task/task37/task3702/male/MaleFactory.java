package com.javarush.task.task37.task3702.male;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;

public class MaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {
        Human human;
        if (age <= KidBoy.MAX_AGE) {
            human = new KidBoy();
            System.out.println(human);
        } else if (age <= TeenBoy.MAX_AGE) {
            human = new TeenBoy();
            System.out.println(human);
        } else {
            human = new Man();
            System.out.println(human);
        }
        return human;
    }
}
