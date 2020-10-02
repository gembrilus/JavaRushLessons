package com.javarush.games.spaceinvaders;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.spaceinvaders.gameobjects.Bullet;
import com.javarush.games.spaceinvaders.gameobjects.EnemyFleet;
import com.javarush.games.spaceinvaders.gameobjects.PlayerShip;
import com.javarush.games.spaceinvaders.gameobjects.Star;

import java.util.ArrayList;
import java.util.List;

public class SpaceInvadersGame extends Game {
    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int COMPLEXITY = 5;
    private static final int PLAYER_BULLETS_MAX = 1;

    private List<Star> stars;
    private EnemyFleet enemyFleet;
    private PlayerShip playerShip;
    private List<Bullet> enemyBullets;
    private List<Bullet> playerBullets;
    private int score = 0;

    private boolean isGameStopped;
    private int animationsCount;

    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        createGame();
    }

    @Override
    public void onTurn(int step) {
        setScore(score);
        moveSpaceObjects();
        check();
        Bullet bullet = enemyFleet.fire(this);
        if (bullet != null){
            enemyBullets.add(bullet);
        }
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {

        switch (key){
            case LEFT: playerShip.setDirection(Direction.LEFT); break;
            case RIGHT: playerShip.setDirection(Direction.RIGHT); break;
            case SPACE: {
                if (isGameStopped) {
                    createGame();
                    break;
                }
                Bullet bullet = playerShip.fire();
                if (playerBullets.size() < PLAYER_BULLETS_MAX && bullet != null){
                    playerBullets.add(bullet);
                }

            }
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        Direction direction = playerShip.getDirection();
        switch (key){
            case RIGHT: if (direction == Direction.RIGHT) playerShip.setDirection(Direction.UP); break;
            case LEFT: if (direction == Direction.LEFT) playerShip.setDirection(Direction.UP); break;
        }
        super.onKeyReleased(key);

    }

    @Override
    public void setCellValueEx(int x, int y, Color cellColor, String value) {
        if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT) return;
        super.setCellValueEx(x, y, cellColor, value);
    }

    private void createGame() {
        score = 0;
        setTurnTimer(40);
        isGameStopped = false;
        animationsCount = 0;
        enemyFleet = new EnemyFleet();
        playerShip = new PlayerShip();
        enemyBullets = new ArrayList<>();
        playerBullets = new ArrayList<>();
        createStars();
        drawScene();
    }

    private void drawScene() {
        drawField();
        enemyFleet.draw(this);
        playerShip.draw(this);
        enemyBullets.forEach(bullet -> bullet.draw(this));
        playerBullets.forEach(bullet -> bullet.draw(this));
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
        playerShip.move();
        enemyBullets.forEach(Bullet::move);
        playerBullets.forEach(Bullet::move);
    }

    private void removeDeadBullets(){
        enemyBullets.removeIf(bullet -> !bullet.isAlive || bullet.y >= HEIGHT - 1);
        playerBullets.removeIf(bullet -> !bullet.isAlive || bullet.y+bullet.height < 0);
    }

    private void check(){
        playerShip.verifyHit(enemyBullets);
        score += enemyFleet.verifyHit(playerBullets);
        enemyFleet.deleteHiddenShips();
        removeDeadBullets();
        if (!playerShip.isAlive){
            stopGameWithDelay();
        }
        double border = enemyFleet.getBottomBorder();
        if (Double.compare(border, playerShip.y) >= 0){
            playerShip.kill();
        }
        if (enemyFleet.getShipsCount() == 0){
            playerShip.win();
            stopGameWithDelay();
        }
    }

    private void stopGame(boolean isWin){
        isGameStopped = true;
        stopTurnTimer();
        if (isWin){
            showMessageDialog(Color.WHITE, "Ви пабидили!", Color.GREEN, 24);
        } else {
            showMessageDialog(Color.WHITE, "Ви слабак!", Color.RED, 24);
        }
    }

    private void stopGameWithDelay(){
        if(++animationsCount >= 10){
            stopGame(playerShip.isAlive);
        }
    }
}
