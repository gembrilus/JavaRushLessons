package com.javarush.games.game2048;

import com.javarush.engine.cell.*;

public class Game2048 extends Game {

    private static final int SIDE = 4;
    private int[][] gameField = new int[SIDE][SIDE];
    private boolean isGameStopped = false;
    private int score = 0;

    private int getMaxTileValue() {
        int max = 0;
        for (int[] row : gameField)
            for (int cell : row)
                if (cell > max) max = cell;
        return max;
    }

    private void rotateClockwise() {
        int[][] temp = new int[SIDE][SIDE];
        for (int i = 0; i < SIDE; i++) {
            for (int k = 0; k < SIDE; k++) {
                temp[k][SIDE - i - 1] = gameField[i][k];
            }
        }
        gameField = temp;
        temp = null;
    }

    private boolean canUserMove() {
        for (int i = 0; i < SIDE; i++) {
            for (int j = 0; j < SIDE; j++) {

                if (gameField[i][j] == 0) return true;

                if (gameField[i][j] == gameField[(j==SIDE-1)?(i == SIDE-1)?i-1:i+1:i][(j==SIDE-1)?j:j+1] ||
                        gameField[i][j] == gameField[(i==SIDE-1)?i-1:i+1][j])
                    return true;
            }
        }
        return false;
    }

    private boolean compressRow(int[] row) {
        int[] temp = new int[row.length];
        boolean isTrue = false;
        for (int i = 0, k = 0; i < SIDE; i++, k++) {
            if (row[i] != 0) temp[k] = row[i];
            else k--;
            if (row[i] != temp[i]) isTrue = true;
        }
        for (int i = 0; i < SIDE; i++) row[i] = temp[i];
        return isTrue;
    }

    private boolean mergeRow(int[] row) {
        boolean isTrue = false;
        for (int i = 0; i < SIDE - 1; i++)
            if (row[i] > 0 && row[i] == row[i + 1]) {
                row[i] += row[i + 1];
                row[i + 1] = 0;
                isTrue = true;
                score+=row[i];
                setScore(score);
            }

        return isTrue;
    }

    private void moveLeft() {
        byte isTrue = 0;
        for (int[] row : gameField) {
            if (compressRow(row) | mergeRow(row)) {
                compressRow(row);
                isTrue++;
            }
        }
        if (isTrue > 0) createNewNumber();
    }

    private void moveRight() {
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
    }

    private void moveUp() {
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
        moveLeft();
        rotateClockwise();
    }

    private void moveDown() {
        rotateClockwise();
        moveLeft();
        rotateClockwise();
        rotateClockwise();
        rotateClockwise();
    }

    private Color getColorByValue(int value) {
        switch (value) {
            case 0:
                return Color.GRAY;
            case 2:
                return Color.LIGHTGRAY;
            case 4:
                return Color.BEIGE;
            case 8:
                return Color.BISQUE;
            case 16:
                return Color.CORAL;
            case 32:
                return Color.CORNFLOWERBLUE;
            case 64:
                return Color.CYAN;
            case 128:
                return Color.YELLOWGREEN;
            case 256:
                return Color.YELLOW;
            case 512:
                return Color.ORANGE;
            case 1024:
                return Color.RED;
            default:
                return Color.PURPLE;
        }
    }

    private void setCellColoredNumber(int x, int y, int value) {
        if (value != 0) setCellValueEx(x, y, getColorByValue(value), Integer.toString(value));
        else setCellValueEx(x, y, getColorByValue(value), "");
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "ВЫ ЭТО СДЕЛАЛИ!\nГЕНИАЛЬНО!", Color.BLACK, 25);
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.GHOSTWHITE, "ЭТО РЕАЛЬНО СЛОЖНО!\nПРОБУЙТЕ ЕЩЕ!", Color.DARKRED, 25);
    }

    private void createNewNumber() {
        int number = (getRandomNumber(10) == 9) ? 4 : 2;
        if (getMaxTileValue() == 2048) {
            win();
            return;
        }
        while (true) {
            int randomCellX = getRandomNumber(SIDE);
            int randomCellY = getRandomNumber(SIDE);
            if (gameField[randomCellX][randomCellY] == 0) {
                gameField[randomCellX][randomCellY] = number;
                break;
            }
        }
    }

    private void createGame() {
        gameField = new int[SIDE][SIDE];
        score = 0;
        setScore(score);
        createNewNumber();
        createNewNumber();
    }

    private void drawScene() {
        for (int i = 0; i < SIDE; i++)
            for (int k = 0; k < SIDE; k++)
                setCellColoredNumber(i, k, gameField[k][i]);
    }

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        if(isGameStopped){
            if (key == Key.SPACE) {
                isGameStopped = false;
                createGame();
                drawScene();
                return;
            }
            return;
        }
        if(!canUserMove()) {
                gameOver();
                return;
        }

        switch (key) {
            case LEFT:
                moveLeft();
                drawScene();
                break;
            case RIGHT:
                moveRight();
                drawScene();
                break;
            case UP:
                moveUp();
                drawScene();
                break;
            case DOWN:
                moveDown();
                drawScene();
                break;
        }

    }
}
