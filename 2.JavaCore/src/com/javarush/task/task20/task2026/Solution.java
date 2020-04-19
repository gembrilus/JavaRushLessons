package com.javarush.task.task20.task2026;

/* 
Алгоритмы-прямоугольники
*/
public class Solution {
    public static void main(String[] args) {
        byte[][] a1 = new byte[][]{
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 0},
                {1, 1, 0, 1}
        };
        byte[][] a2 = new byte[][]{
                {1, 0, 0, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0},
                {1, 0, 0, 1}
        };

        int count1 = getRectangleCount(a1);
        System.out.println("count = " + count1 + ". Должно быть 2");
        int count2 = getRectangleCount(a2);
        System.out.println("count = " + count2 + ". Должно быть 4");
    }

    public static int getRectangleCount(byte[][] a) {
        int count = 0;

        for(int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                if (a[i][j] == 1) {
                    getRectangle(i, j, a);
                    count++;
                }
            }
        }
        return count;
    }

    private static void getRectangle(int x, int y, byte[][] a){
        a[x][y] = 0;
        for(int i = 0; i <= 1; i++){
            for(int j = 0; j <= 1; j++){
                if( x + i < a.length &&
                    y + j < a[0].length){
                    if(i == 0 && j == 0) continue;
                    if(a[x+i][y+j] == 1) {
                        getRectangle(x+i, y+j, a);
                    }
                }
            }
        }
    }
}