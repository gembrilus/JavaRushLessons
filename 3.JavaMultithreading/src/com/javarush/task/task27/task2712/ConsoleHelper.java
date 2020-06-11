package com.javarush.task.task27.task2712;

import com.javarush.task.task27.task2712.kitchen.Dish;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleHelper {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message) {
        System.out.println(message);
    }

    public static String readString() throws IOException {
        return reader.readLine();
    }

    public static List<Dish> getAllDishesForOrder() throws IOException {
        List<Dish> dishes = new ArrayList<>();
        String allDishes = Dish.allDishesToString();
        String input;
        do {
            writeMessage(allDishes);
            writeMessage("Выберите блюдо из списка: ");
            input = readString();
            if (input.equals("exit")) {
                return dishes;
            }
            if (!allDishes.contains(input)) {
                writeMessage("Нет такого блюда.");
                continue;
            }
            dishes.add(Dish.valueOf(input));
        } while (true);
    }
}
