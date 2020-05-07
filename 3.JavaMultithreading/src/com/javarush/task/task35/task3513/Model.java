package com.javarush.task.task35.task3513;

import java.util.*;
import java.util.stream.Collectors;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;

    int score = 0;
    int maxTile = 0;

    private boolean isSaveNeeded = true;
    private final Stack<Tile[][]> previousStates;
    private final Stack<Integer> previousScores;

    public Model() {
        resetGameTiles();
        previousStates = new Stack<>();
        previousScores = new Stack<>();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    private List<Tile> getEmptyTiles() {
        return Arrays.stream(gameTiles)
                .flatMap(Arrays::stream)
                .filter(Tile::isEmpty)
                .collect(Collectors.toList());
    }

    public void addTile() {
        int weight = (Math.random() < 0.9) ? 2 : 4;
        List<Tile> emptyTiles = getEmptyTiles();
        if (emptyTiles.size() > 0) {
            emptyTiles.get((int) (Math.random() * emptyTiles.size())).value = weight;
        }
    }

    public void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }
        addTile();
        addTile();
    }

    private boolean compressTiles(Tile[] tiles) {
        boolean isTrue = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            if (!tiles[i].isEmpty()) {
                continue;
            }
            for (int j = i + 1; j < FIELD_WIDTH; j++) {
                if (!tiles[j].isEmpty()) {
                    Tile temp = tiles[i];
                    tiles[i] = tiles[j];
                    tiles[j] = temp;
                    isTrue = true;
                    break;
                }
            }
        }
        return isTrue;
    }

    private boolean mergeTiles(Tile[] tiles) {
        boolean isTrue = false;
        if (tiles[0].value == 0 || tiles[1].value == 0) {
            return false;
        }
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            int first = tiles[i].value;
            int second = tiles[i + 1].value;
            if (first > 0 && first == second) {
                int newValue = first + second;
                score += newValue;
                maxTile = Math.max(maxTile, newValue);
                tiles[i].value = newValue;
                tiles[i + 1].value = 0;
                compressTiles(tiles);
                isTrue = true;
            }
        }
        return isTrue;
    }

    private void rotateGameTiles() {
        Tile[][] temp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int k = 0; k < FIELD_WIDTH; k++) {
                temp[k][FIELD_WIDTH - i - 1] = gameTiles[i][k];
            }
        }
        gameTiles = temp;
    }

    public void left() {
        if (isSaveNeeded) {
            saveState(gameTiles);
        }
        boolean isChanged = false;
        for (Tile[] row : gameTiles) {
            boolean isCompressed = compressTiles(row);
            boolean isMerged = mergeTiles(row);
            if (isCompressed | isMerged) {
                isChanged = true;
            }
        }
        if (isChanged) {
            addTile();
        }
        isSaveNeeded = true;
    }

    public void right() {
        saveState(gameTiles);
        rotateGameTiles();
        rotateGameTiles();
        left();
        rotateGameTiles();
        rotateGameTiles();
    }

    public void up() {
        saveState(gameTiles);
        rotateGameTiles();
        rotateGameTiles();
        rotateGameTiles();
        left();
        rotateGameTiles();
    }

    public void down() {
        saveState(gameTiles);
        rotateGameTiles();
        left();
        rotateGameTiles();
        rotateGameTiles();
        rotateGameTiles();
    }

    public boolean canMove() {
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {

                if (gameTiles[i][j].value == 0) {
                    return true;
                }

                if (gameTiles[i][j].value == gameTiles[(j == FIELD_WIDTH - 1) ? (i == FIELD_WIDTH - 1) ? i - 1 : i + 1 : i][(j == FIELD_WIDTH - 1) ? j : j + 1].value ||
                        gameTiles[i][j].value == gameTiles[(i == FIELD_WIDTH - 1) ? i - 1 : i + 1][j].value) {
                    return true;
                }
            }
        }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] copy = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                copy[i][j] = new Tile(tiles[i][j].value);
            }
        }
        previousStates.push(copy);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousStates.empty() || previousScores.empty()) {
            return;
        }
        gameTiles = previousStates.pop();
        score = previousScores.pop();
    }

    public void randomMove() {
        int n = ((int) (Math.random() * 100)) % 4;
        switch (n) {
            case 0:
                left();
                break;
            case 1:
                right();
                break;
            case 2:
                up();
                break;
            case 3:
                down();
                break;
            default:
                break;
        }
    }

    public boolean hasBoardChanged() {
        return Arrays.stream(gameTiles)
                .flatMap(Arrays::stream)
                .mapToInt(cell -> cell.value)
                .sum() !=
                Arrays.stream(previousStates.peek())
                        .flatMap(Arrays::stream)
                        .mapToInt(cell -> cell.value)
                        .sum();
    }

    public MoveEfficiency getMoveEfficiency(Move move) {
        move.move();
        MoveEfficiency moveEfficiency = hasBoardChanged() ? new MoveEfficiency(
                getEmptyTiles().size(),
                score,
                move
        ) : new MoveEfficiency(-1, 0, move);
        rollback();
        return moveEfficiency;
    }

    public void autoMove() {
        PriorityQueue<MoveEfficiency> queue = new PriorityQueue<>(4, Collections.reverseOrder());
        queue.offer(getMoveEfficiency(this::left));
        queue.offer(getMoveEfficiency(this::right));
        queue.offer(getMoveEfficiency(this::up));
        queue.offer(getMoveEfficiency(this::down));
        if (queue.peek() != null) {
            queue.peek().getMove().move();
        }
    }

}
