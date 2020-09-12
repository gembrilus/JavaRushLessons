package com.javarush.task.task39.task3912;

/* 
Максимальная площадь
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static int maxSquare(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return 0;

        int width = matrix[0].length;
        int height = matrix.length;

        if (height == 1 || width == 1){
            for (int[] row: matrix){
                for (int i: row){
                    if (i == 1) return 1;
                }
            }
            return 0;
        }

        int side = 0;
        for (int i = 1; i < height; i++){
            for (int j = 1; j < width; j++) {
                if (matrix[i][j] == 1) {
                    matrix[i][j] = Math.min(
                            matrix[i - 1][j],
                            Math.min(
                                    matrix[i - 1][j - 1],
                                    matrix[i][j - 1]
                            )
                    ) + 1;

                    if (matrix[i][j] > side) {
                        side = matrix[i][j];
                    }
                }
            }
        }
        return side * side;
    }
}
