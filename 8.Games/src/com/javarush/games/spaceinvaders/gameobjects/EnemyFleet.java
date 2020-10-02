package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.engine.cell.Game;
import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.ArrayList;
import java.util.List;

public class EnemyFleet {
    private static final int ROWS_COUNT = 3;
    private static final int COLUMNS_COUNT = 10;
    private static final int STEP = ShapeMatrix.ENEMY.length+1;
    private List<EnemyShip> ships;
    private Direction direction = Direction.RIGHT;

    public EnemyFleet() {
        createShips();
    }

    public void draw(Game game){
        for(EnemyShip ship: ships) ship.draw(game);
    }
    private void createShips(){
        ships = new ArrayList<>();
        for (int i = 0; i < COLUMNS_COUNT; i++){
            for (int j = 0; j < ROWS_COUNT; j++){
                ships.add(new EnemyShip(i*STEP, j*STEP+12));
            }
        }
        ships.add(new Boss((STEP * COLUMNS_COUNT >> 1) - (ShapeMatrix.BOSS_ANIMATION_FIRST.length >> 1) - 1, 5));
    }

    public Bullet fire(Game game){
        if(ships.isEmpty()) return null;
        int random = game.getRandomNumber(100 / SpaceInvadersGame.COMPLEXITY);
        if (random > 0) return null;
        random = game.getRandomNumber(ships.size());
        return ships.get(random).fire();
    }

    public double getBottomBorder(){
        return ships.stream().mapToDouble(ship -> ship.y + ship.height).max().orElse(SpaceInvadersGame.HEIGHT);
    }

    public int getShipsCount(){
        return ships.size();
    }

    public void move(){
        if(ships.size() == 0) return;
        else if(direction == Direction.LEFT &&
                    getLeftBorder() < 0) {
            direction = Direction.RIGHT;
            for(EnemyShip ship: ships) ship.move(Direction.DOWN, getSpeed());
        }
        else if(direction == Direction.RIGHT &&
                    getRightBorder() > SpaceInvadersGame.WIDTH) {
            direction = Direction.LEFT;
            for(EnemyShip ship: ships) ship.move(Direction.DOWN, getSpeed());
        }
        else for (EnemyShip ship: ships) ship.move(direction, getSpeed());
    }

    private double getLeftBorder(){
        double min = ships.get(0).x;
        for (EnemyShip ship : ships) {
            double x = ship.x;
            if (x < min) min = x;
        }
        return min;
    }

    private double getRightBorder(){
        double max = 0;
        for (EnemyShip ship : ships) {
            double x = ship.x;
            if (x+ship.width > max) max = x+ship.width;
        }
        return max;
    }

    public int verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) return 0;
        final int[] score = {0};
        ships.forEach(ship -> {
            boolean isCollision = false;
            for (Bullet bullet: bullets) {
                isCollision |= ship.isCollision(bullet);
                if (isCollision && ship.isAlive && bullet.isAlive) {
                    score[0] += ship.score;
                    ship.kill();
                    bullet.kill();
                }
            }
        });
        return score[0];
    }

    public void deleteHiddenShips(){
        ships.removeIf(ship -> !ship.isVisible());
    }

    private double getSpeed(){
        return Math.min(2.0, 3.0 / ships.size());
    }
}
