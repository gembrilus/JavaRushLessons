package com.javarush.games.moonlander;

import com.javarush.engine.cell.*;

public class MoonLanderGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        if(score > 0) score--;
        check();
        setScore(score);
        rocket.move(isUpPressed, isLeftPressed, isRightPressed);
        drawScene();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if(x < WIDTH && x > 0 && y < HEIGHT && y > 0) super.setCellColor(x, y, color);
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case UP:
                isUpPressed = true;
                break;
            case LEFT:
                isLeftPressed = true;
                isRightPressed = false;
                break;
            case RIGHT:
                isRightPressed = true;
                isLeftPressed = false;
                break;
            case ESCAPE: System.exit(0); break;
            case SPACE:
                if(isGameStopped) createGame();
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case UP:
                isUpPressed = false;
                break;
            case LEFT:
                isLeftPressed = false;
                break;
            case RIGHT:
                isRightPressed = false;
                break;
        }
    }

    private void createGame() {
        isGameStopped = false;
        isUpPressed = false;
        isLeftPressed = false;
        isRightPressed = false;
        score = 1000;
        setTurnTimer(50);
        createGameObjects();
        drawScene();
    }

    private void createGameObjects() {
        rocket = new Rocket(WIDTH/2, 0);
        landscape = new GameObject(0, 25, ShapeMatrix.LANDSCAPE);
        platform = new GameObject(23, MoonLanderGame.HEIGHT - 1, ShapeMatrix.PLATFORM);
    }

    private void drawScene() {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++)
                setCellColor(i, j, Color.GRAY);
        landscape.draw(this);
        rocket.draw(this);
    }

    private void check(){
        if(rocket.isCollision(landscape) && !rocket.isCollision(platform)) gameOver();
        else if(rocket.isCollision(platform) && rocket.isStopped()) win();
    }
    private void win(){
        rocket.land();
        isGameStopped = true;
        showMessageDialog(Color.BEIGE, "ВЫ ЭТО СДЕЛАЛИ!", Color.BLACK, 25);
        stopTurnTimer();
    }
    private void gameOver(){
        rocket.crash();
        isGameStopped = true;
        score = 0;
        showMessageDialog(Color.GHOSTWHITE, "ЭТО РЕАЛЬНО СЛОЖНО!\nПРОБУЙТЕ ЕЩЕ!", Color.DARKRED, 25);
        stopTurnTimer();
    }


    private Rocket rocket;
    private GameObject landscape;
    private GameObject platform;
    private boolean isUpPressed;
    private boolean isLeftPressed;
    private boolean isRightPressed;
    private boolean isGameStopped;
    private int score;
}
