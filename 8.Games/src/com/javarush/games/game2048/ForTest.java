package com.javarush.games.game2048;

public class ForTest {
    private int SIDE = 4;
    private int[][] gameField;

    ForTest(int[][] array){
        this.gameField = array;
    }

    public int[][] getGameField() {
        return gameField;
    }

    private boolean canUserMove() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {
                if (gameField[i][j] == 0) return true;

                if (gameField[i][j] == gameField[(j==SIDE-1)?i+1:i][(j==SIDE-1)?j:j+1] ||
                        gameField[i][j] == gameField[(i==SIDE-1)?i-1:i+1][j])
                    return true;
            }
        }
        return false;
    }



}
