package com.javarush.games.minesweeper;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.List;

public class MinesweeperGame extends Game {

    private static final int SIDE = 9;
    private static final String MINE = "\uD83D\uDCA3";
    private static final String FLAG = "\uD83D\uDEA9";
    private int countMinesOnField = 0;
    private int countClosedTiles = SIDE * SIDE;
    private int countFlags = 0;
    private boolean isGameStopped;
    private GameObject[][] gameField = new GameObject[SIDE][SIDE];
    private int score = 0;

    private void countMineNeighbors() {
        for (GameObject[] row : gameField)
            for (GameObject cell : row)
                if (!cell.isMine)
                    for (GameObject el : getNeighbors(cell))
                        if (el.isMine) cell.countMineNeighbors++;
    }

    private List<GameObject> getNeighbors(GameObject gameObject) {
        List<GameObject> temp = new ArrayList<>();
        for (int i = -1; i <= 1; i++)
            for (int k = -1; k <= 1; k++) {
                if (i == 0 && k == 0) continue;
                if (gameObject.x + i >= 0 &&
                        gameObject.x + i < SIDE &&
                        gameObject.y + k >= 0 &&
                        gameObject.y + k < SIDE) {
                    temp.add(gameField[gameObject.y + k][gameObject.x + i]);
                }
            }
        return temp;
    }

    private void openTile(int x, int y) {
        if (isGameStopped || gameField[y][x].isFlag || gameField[y][x].isOpen) return;
        gameField[y][x].isOpen = true;
        countClosedTiles--;
        if (gameField[y][x].isMine) {
            setCellValueEx(x, y, Color.RED, MINE);
            gameOver();
        }
        else {
            setScore(score += 5);
            setCellColor(x, y, Color.GREY);
            if (countClosedTiles == countMinesOnField) win();
            if (gameField[y][x].countMineNeighbors != 0) {
                setCellNumber(x, y, gameField[y][x].countMineNeighbors);
            } else {
                setCellValue(x, y, "");
                for (GameObject neigh : getNeighbors(gameField[y][x]))
                    openTile(neigh.x, neigh.y);
            }
        }
    }

    private void markTile(int x, int y) {
        if (!isGameStopped && !gameField[y][x].isOpen) {
            if (!gameField[y][x].isFlag && countFlags > 0) {
                gameField[y][x].isFlag = true;
                countFlags--;
                setCellValue(x, y, FLAG);
                setCellColor(x, y, Color.GOLD);
            } else if (gameField[y][x].isFlag) {
                gameField[y][x].isFlag = false;
                countFlags++;
                setCellValue(x, y, "");
                setCellColor(x, y, Color.GOLD);
            }
        }
    }

    private void restart(){
            isGameStopped = false;
            countClosedTiles = SIDE * SIDE;
            countMinesOnField = 0;
            score = 0;
            setScore(score);
            createGame();
    }

    private void gameOver() {
        isGameStopped = true;
        showMessageDialog(Color.DARKGREY, "Не повезло. Вы подорвались!", Color.WHITE, 25);
    }

    private void win() {
        isGameStopped = true;
        showMessageDialog(Color.LIGHTGREEN, "Вы победили! Поздравляем!", Color.DARKGREY, 25);
    }

    private void createGame() {
        for (int i = 0; i < SIDE; i++)
            for (int j = 0; j < SIDE; j++) {
                boolean mine = false;
                if (getRandomNumber(10) == 9) {
                    countMinesOnField++;
                    mine = true;
                }
                gameField[j][i] = new GameObject(i, j, mine);
                setCellValue(i, j, "");
                setCellColor(i, j, Color.GOLD);
            }
        countFlags = countMinesOnField;
        countMineNeighbors();
    }

    private boolean isField(int x, int y){
        return x >= 0 && x < SIDE && y >= 0 && y < SIDE;
    }

    @Override
    public void initialize() {
        setScreenSize(SIDE, SIDE);
        createGame();
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isField(x, y)) return;
        if(isGameStopped){
            restart();
            return;
        }
        openTile(x, y);
    }

    @Override
    public void onMouseRightClick(int x, int y) {
        if(!isField(x, y)) return;
        markTile(x, y);
    }
}
