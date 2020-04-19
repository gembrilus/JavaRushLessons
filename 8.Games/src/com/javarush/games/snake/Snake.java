package com.javarush.games.snake;

import com.javarush.engine.cell.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Snake {

    public boolean isAlive = true;

    private static final String HEAD_SIGN = "\uD83D\uDC7E";
    private static final String BODY_SIGN = "\u26AB";
    private List<GameObject> snakeParts = new ArrayList<>();
    private Direction direction = Direction.LEFT;

    public Snake(int x, int y) {
        Collections.addAll(
                snakeParts,
                new GameObject(x, y),
                new GameObject(x + 1, y),
                new GameObject(x + 2, y));
    }

    public int getLength() {
        return snakeParts.size();
    }

    public void setDirection(Direction direction) {
        int X0 = snakeParts.get(0).x;
        int X1 = snakeParts.get(1).x;
        int Y0 = snakeParts.get(0).y;
        int Y1 = snakeParts.get(1).y;

        switch (direction) {
            case UP:
                if (X0 != X1)
                    this.direction = (this.direction == Direction.DOWN) ? Direction.DOWN : Direction.UP;
                break;
            case DOWN:
                if (X0 != X1)
                    this.direction = (this.direction == Direction.UP) ? Direction.UP : Direction.DOWN;
                break;
            case RIGHT:
                if (Y0 != Y1)
                    this.direction = (this.direction == Direction.LEFT) ? Direction.LEFT : Direction.RIGHT;
                break;
            case LEFT:
                if (Y0 != Y1)
                    this.direction = (this.direction == Direction.RIGHT) ? Direction.RIGHT : Direction.LEFT;
                break;
        }

    }

    public void move(Apple apple) {
        GameObject head = createNewHead();
        if (head.x < 0 ||
                head.y < 0 ||
                head.x > SnakeGame.WIDTH - 1 ||
                head.y > SnakeGame.HEIGHT - 1) {
            isAlive = false;
            return;
        }
        isAlive = !checkCollision(head);
        if (!isAlive) return;
        snakeParts.add(0, head);
        if (head.x != apple.x || head.y != apple.y) removeTail();
        else apple.isAlive = false;
    }

    public GameObject createNewHead() {
        int headX = snakeParts.get(0).x;
        int headY = snakeParts.get(0).y;
        GameObject gameObject = new GameObject(headX, headY);

        switch (direction) {
            case UP:
                gameObject = new GameObject(headX, headY - 1);
                break;
            case DOWN:
                gameObject = new GameObject(headX, headY + 1);
                break;
            case LEFT:
                gameObject = new GameObject(headX - 1, headY);
                break;
            case RIGHT:
                gameObject = new GameObject(headX + 1, headY);
                break;
        }
        return gameObject;
    }

    public void removeTail() {
        snakeParts.remove(snakeParts.size() - 1);
    }

    public void draw(Game game) {

        for (int i = 0; i < snakeParts.size(); i++) {
            if (i == 0)
                game.setCellValueEx(
                        snakeParts.get(i).x,
                        snakeParts.get(i).y,
                        Color.NONE,
                        HEAD_SIGN,
                        isAlive ? Color.BLACK : Color.RED,
                        75);
            else
                game.setCellValueEx(
                        snakeParts.get(i).x,
                        snakeParts.get(i).y,
                        Color.NONE,
                        BODY_SIGN,
                        isAlive ? Color.BLACK : Color.RED,
                        75);
        }
    }

    public boolean checkCollision(GameObject partOfSnake) {
        for (GameObject gameObject : snakeParts)
            if (gameObject.x == partOfSnake.x && gameObject.y == partOfSnake.y) return true;
        return false;
    }

}
