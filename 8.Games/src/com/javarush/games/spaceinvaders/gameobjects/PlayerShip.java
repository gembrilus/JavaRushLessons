package com.javarush.games.spaceinvaders.gameobjects;

import com.javarush.games.spaceinvaders.Direction;
import com.javarush.games.spaceinvaders.ShapeMatrix;
import com.javarush.games.spaceinvaders.SpaceInvadersGame;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PlayerShip extends Ship {

    private Direction direction = Direction.UP;

    public PlayerShip() {
        super(SpaceInvadersGame.WIDTH / 2.0, SpaceInvadersGame.HEIGHT - ShapeMatrix.PLAYER.length - 1);
        setStaticView(ShapeMatrix.PLAYER);
    }

    public void verifyHit(List<Bullet> bullets) {
        if (bullets.isEmpty()) return;
        if (isAlive) {
            Map<Boolean, List<Bullet>> bulletsByGroup = bullets.stream()
                    .filter(bullet -> bullet.isAlive)
                    .collect(Collectors.partitioningBy(this::isCollision));
            if (bulletsByGroup.get(true).isEmpty()) return;

            kill();
            bulletsByGroup.get(true).get(0).kill();
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction newDirection) {
        if (newDirection != Direction.DOWN) {
            direction = newDirection;
        }
    }

    public void move() {
        if (!isAlive) return;
        if (direction == Direction.LEFT) x -= 1;
        if (direction == Direction.RIGHT) x += 1;
        checkX();

    }

    private void checkX() {
        if (x < 0) {
            x = 0;
        }
        if (x + width > SpaceInvadersGame.WIDTH) {
            x = SpaceInvadersGame.WIDTH - width;
        }
    }

    @Override
    public Bullet fire() {
        if (!isAlive) return null;
        return new Bullet(x + 2, y - ShapeMatrix.BULLET.length, Direction.UP);
    }

    public void win(){
        setStaticView(ShapeMatrix.WIN_PLAYER);
    }

    @Override
    public void kill() {
        if (!isAlive) return;
        isAlive = false;
        setAnimatedView(false, ShapeMatrix.KILL_PLAYER_ANIMATION_FIRST, ShapeMatrix.KILL_PLAYER_ANIMATION_SECOND, ShapeMatrix.KILL_PLAYER_ANIMATION_THIRD, ShapeMatrix.DEAD_PLAYER);
    }
}
