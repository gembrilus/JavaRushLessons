package com.javarush.task.task30.task3001;

import java.math.BigInteger;

/*
Конвертер систем счислений
*/
public class Solution {
    public static void main(String[] args) {

        Number number = new Number(NumberSystemType._10, "6");
        Number result = convertNumberToOtherNumberSystem(number, NumberSystemType._2);
        System.out.println(result);    //expected 110

        number = new Number(NumberSystemType._16, "6df");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._8);
        System.out.println(result);    //expected 3337

        number = new Number(NumberSystemType._16, "abcdefabcdef");
        result = convertNumberToOtherNumberSystem(number, NumberSystemType._16);
        System.out.println(result);    //expected abcdefabcdef

    }

    public static Number convertNumberToOtherNumberSystem(
            Number number,
            NumberSystem expectedNumberSystem
    ) throws NumberFormatException {

        String digit = number.getDigit();
        int currentNumberSystemValue = number.getNumberSystem().getNumberSystemIntValue();

        int expectedNumberSystemValue = expectedNumberSystem.getNumberSystemIntValue();

        BigInteger digitValue = new BigInteger(digit, currentNumberSystemValue);

        if (currentNumberSystemValue == expectedNumberSystemValue){
            return number;
        }

        String newDigit = digitValue.toString(expectedNumberSystemValue);

        return new Number(expectedNumberSystem, newDigit);
    }

}
