package com.javarush.task.task30.task3012;

/* 
Получи заданное число
*/

public class Solution {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.createExpression(74);
        solution.createExpression(1234);
        solution.createExpression(3000);
    }

    public void createExpression(int number) {
        StringBuilder trit = new StringBuilder()
                .append(number)
                .append(" =");
        int offset = 0;
        int koef = 1;
        do {
            switch (number % 3) {
                case 2:
                    trit.append(" - ").append(koef);
                    offset++;
                    break;
                case 1:
                    trit.append(" + ").append(koef);
                    break;
                default:
                    break;
            }
            number = number / 3 + offset;
            koef *= 3;
            offset = 0;
        } while (number > 0);
        System.out.println(trit.toString());
    }
}