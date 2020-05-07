package com.javarush.task.task35.task3513;

public class MoveEfficiency implements Comparable<MoveEfficiency> {
    private int numberOfEmptyTiles;
    private int score;
    private Move move;

    public MoveEfficiency(int numberOfEmptyTiles, int score, Move move) {
        this.numberOfEmptyTiles = numberOfEmptyTiles;
        this.score = score;
        this.move = move;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public int compareTo(MoveEfficiency efficiency) {
        int result = Integer.compare(numberOfEmptyTiles, efficiency.numberOfEmptyTiles);
        if (result == 0){
            result = Integer.compare(score, efficiency.score);
        }
        return result;
    }
}
