package com.javarush.task.task37.task3702.female;

import com.javarush.task.task37.task3702.AbstractFactory;
import com.javarush.task.task37.task3702.Human;
import com.javarush.task.task37.task3702.male.KidBoy;
import com.javarush.task.task37.task3702.male.TeenBoy;

public class FemaleFactory implements AbstractFactory {

    @Override
    public Human getPerson(int age) {
        Human human;
        if (age <= KidBoy.MAX_AGE) {
            human = new KidGirl();
            System.out.println(human);
        } else if (age <= TeenBoy.MAX_AGE) {
            human = new TeenGirl();
            System.out.println(human);
        } else {
            human = new Woman();
            System.out.println(human);
        }
        return human;
    }
}
