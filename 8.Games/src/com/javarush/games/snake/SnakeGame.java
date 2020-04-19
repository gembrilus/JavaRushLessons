package com.javarush.games.snake;

import com.javarush.engine.cell.*;

public class SnakeGame extends Game {
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    private static final int GOAL = 28;

    private Snake snake;
    private Apple apple;
    private int turnDelay;
    private boolean isGameStopped;
    private int score;


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
            createGame();
    }

    private void createGame(){
        isGameStopped = false;
        score = 0;
        turnDelay = 300;
        snake = new Snake(WIDTH/2, HEIGHT/2);
        setScore(score);
        createNewApple();
        drawScene();
        setTurnTimer(turnDelay);
    }

    private void drawScene() {
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                setCellValueEx(x, y, Color.DARKSEAGREEN, "");
            }
        }
        snake.draw(this);
        apple.draw(this);
    }


    private void createNewApple(){
        do {
            apple = new Apple(getRandomNumber(WIDTH), getRandomNumber(HEIGHT));
        } while (snake.checkCollision(apple));
    }

    private void win(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "ПОЗДРАВЛЯЕМ!\nЗмейка разжирела и стала удавом!", Color.BLACK, 25);
    }

    private void gameOver(){
        stopTurnTimer();
        isGameStopped = true;
        showMessageDialog(Color.RED, "Ой! Змейка переела и устала!", Color.BLACK, 25);
    }

    @Override
    public void onTurn(int x){
        snake.move(apple);
        if (!apple.isAlive){
            score+=5;
            setScore(score);
            turnDelay-=10;
            setTurnTimer(turnDelay);
            createNewApple();
        }
        if(!snake.isAlive) gameOver();
        if(snake.getLength() > GOAL) win();
        drawScene();
    }

    @Override
    public void onKeyPress(Key key){
        switch (key){
            case UP: snake.setDirection(Direction.UP); break;
            case DOWN: snake.setDirection(Direction.DOWN); break;
            case RIGHT: snake.setDirection(Direction.RIGHT); break;
            case LEFT: snake.setDirection(Direction.LEFT); break;
            case SPACE: if(isGameStopped) createGame();
        }
    }
}
