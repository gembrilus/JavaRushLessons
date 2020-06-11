package com.javarush.task.task34.task3412;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/* 
Добавление логирования в класс
*/

public class Solution {
    private static final Logger logger = LoggerFactory.getLogger(Solution.class);

    private int value1;
    private String value2;
    private Date value3;

    public Solution(int value1, String value2, Date value3) {
        this.value1 = value1;
        this.value2 = value2;
        this.value3 = value3;
        logger.debug(String.format("Начальная инициализация полей value1 = %d, value2 = %s, value3 = %s%n", value1, value2, value3));
    }

    public static void main(String[] args) {
        Solution solution = new Solution(1, "Max", new Date());
        solution.calculateAndSetValue3(100);
        solution.printString();
        solution.printDateAsLong();
        solution.divide(1, 3);
    }

    public void calculateAndSetValue3(long value) {
        logger.trace("Выподняется метод 'calculateAndSetValue3'\n");
        value -= 133;
        if (value > Integer.MAX_VALUE) {
            value1 = (int) (value / Integer.MAX_VALUE);
            logger.debug(String.format("Поле поменяло значение: value1 = %d%n", value1));
        } else {
            value1 = (int) value;
            logger.debug(String.format("Поле поменяло значение: value1 = %d%n", value1));
        }
    }

    public void printString() {
        logger.trace("Выподняется метод 'printString'\n");
        if (value2 != null) {
            System.out.println(value2.length());
        }
    }

    public void printDateAsLong() {
        logger.trace("Выподняется метод 'printDateAsLong'\n");
        if (value3 != null) {
            System.out.println(value3.getTime());
        }
    }

    public void divide(int number1, int number2) {
        logger.trace("Выподняется метод 'divide'\n");
        try {
            System.out.println(number1 / number2);
        } catch (ArithmeticException e) {
            logger.error(e.getMessage());
        }
    }

    public void setValue1(int value1) {
        this.value1 = value1;
        logger.debug(String.format("В поле value1 установлено новое значние: %d%n", value1));
    }

    public void setValue2(String value2) {
        this.value2 = value2;
        logger.debug(String.format("В поле value2 установлено новое значние: %s%n", value2));
    }

    public void setValue3(Date value3) {
        this.value3 = value3;
        logger.debug(String.format("В поле value3 установлено новое значние: %s%n", value3));
    }
}
