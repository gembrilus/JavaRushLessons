package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;

    private List<Star> stars;
    private EnemyFleet enemyFleet;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        moveSpaceObjects();
        drawScene();
    }

    private void createGame() {
        setTurnTimer(40);
        enemyFleet = new EnemyFleet();
        createStars();
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
    }

    private void drawField() {
        for (int y = 0; y < WIDTH; y++)
            for (int x = 0; x < HEIGHT; x++)
                setCellValueEx(x, y, Color.BLACK, "");
        for (Star star : stars) star.draw(this);
    }

    private void createStars() {
        stars = new ArrayList<Star>();
        stars.add(new Star(1, 1));
        stars.add(new Star(50, 50));
        stars.add(new Star(1, 50));
        stars.add(new Star(50, 1));
        stars.add(new Star(25, 11));
        stars.add(new Star(11, 19));
        stars.add(new Star(2, 25));
        stars.add(new Star(30, 32));
    }

    private void moveSpaceObjects() {
        enemyFleet.move();
    }
}
