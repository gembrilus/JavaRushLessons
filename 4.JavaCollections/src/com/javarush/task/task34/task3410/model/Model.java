package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.nio.file.Paths;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;

    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
    private LevelLoader levelLoader = new LevelLoader(Paths.get("../res/levels.txt"));

    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        ++currentLevel;
        restart();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction) {
        return gameObjects.getWalls().stream()
                .anyMatch(wall -> gameObject.isCollision(wall, direction));
    }

    public boolean checkBoxCollisionAndMoveIfAvailable(Direction direction) {
        boolean isCollision = false;

        Player player = gameObjects.getPlayer();
        Set<Wall> walls = gameObjects.getWalls();
        Set<Box> boxes = gameObjects.getBoxes();
        Stream<GameObject> wallsAndBoxes = Stream.concat(walls.stream(), boxes.stream());

        Set<Box> collisionBoxes = boxes.stream().filter(box -> player.isCollision(box, direction)).collect(Collectors.toSet());

        if (collisionBoxes.isEmpty()) return false;

        for (Box box : collisionBoxes) {
            boolean isAnotherWallOrBox = wallsAndBoxes.anyMatch(obj -> box.isCollision(obj, direction));
            isCollision |= isAnotherWallOrBox;
            if(!isAnotherWallOrBox) {
                move(box, direction);
            }
        }
        return isCollision;
    }

    public void checkCompletion() {
        class Pair {
            private int x;
            private int y;

            public Pair(int x, int y) {
                this.x = x;
                this.y = y;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;

                Pair pair = (Pair) o;

                if (x != pair.x) return false;
                return y == pair.y;
            }

            @Override
            public int hashCode() {
                int result = x;
                result = 31 * result + y;
                return result;
            }
        }
        Set<Pair> boxes = gameObjects.getBoxes().stream()
                .map(box -> new Pair(box.getX(), box.getY()))
                .collect(Collectors.toSet());

        Set<Pair> homes = gameObjects.getHomes().stream()
                .map(home -> new Pair(home.getX(), home.getY()))
                .collect(Collectors.toSet());
        if(boxes.containsAll(homes)){
            eventListener.levelCompleted(currentLevel);
        }
    }

    public void move(Direction direction) {
        Player player = gameObjects.getPlayer();
        if(checkWallCollision(player, direction)) {
            System.out.println("Collision with wall!");
            return;
        }
        if(checkBoxCollisionAndMoveIfAvailable(direction)) {
            System.out.println("Collision with box. Cannot move!");
            return;
        }
        move(player, direction);
        checkCompletion();
    }

    private void move(Movable movable, Direction direction){
        switch (direction){
            case UP: movable.move(0, -FIELD_CELL_SIZE); break;
            case DOWN: movable.move(0, FIELD_CELL_SIZE); break;
            case LEFT: movable.move(-FIELD_CELL_SIZE, 0); break;
            case RIGHT: movable.move(FIELD_CELL_SIZE, 0); break;
        }
    }
}
