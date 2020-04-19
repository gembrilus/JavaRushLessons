package com.javarush.games.racer;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;
import com.javarush.games.racer.road.RoadManager;

public class RacerGame extends Game {

    public static final int WIDTH = 64;
    public static final int HEIGHT = 64;
    public static final int CENTER_X = WIDTH / 2;
    public static final int ROADSIDE_WIDTH = 14;


    @Override
    public void initialize() {
        setScreenSize(WIDTH, HEIGHT);
        showGrid(false);
        createGame();
    }

    @Override
    public void setCellColor(int x, int y, Color color) {
        if (x >= 0 && x < WIDTH &&
                y >= 0 && y < HEIGHT) super.setCellColor(x, y, color);
    }

    @Override
    public void onTurn(int step) {
        if(roadManager.getPassedCarsCount() >= RACE_GOAL_CARS_COUNT)
            finishLine.show();
        if(roadManager.checkCrush(player)){
            gameOver();
            drawScene();
            return;
        }
        moveAll();
        roadManager.generateNewRoadObjects(this);
        drawScene();
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key){
            case UP:    player.speed = 2;
            case RIGHT: player.setDirection(Direction.RIGHT); break;
            case LEFT:  player.setDirection(Direction.LEFT); break;
            case SPACE: if(isGameStopped) createGame();
        }
    }

    @Override
    public void onKeyReleased(Key key) {
        switch (key){
            case UP:    player.speed = 1;
            case LEFT:
                if(player.getDirection() == Direction.LEFT)
                    player.setDirection(Direction.NONE);
                break;
            case RIGHT:
                if(player.getDirection() == Direction.RIGHT)
                    player.setDirection(Direction.NONE);
                break;
        }
    }

    private void createGame() {
        isGameStopped = false;
        setTurnTimer(40);
        roadMarking = new RoadMarking();
        player = new PlayerCar();
        roadManager = new RoadManager();
        finishLine = new FinishLine();
        progressBar = new ProgressBar(RACE_GOAL_CARS_COUNT);
        drawScene();
    }

    private void gameOver(){
        isGameStopped = true;
        showMessageDialog(Color.NONE, "Вы погибли. Не сдавайтесь!", Color.RED, 24);
        stopTurnTimer();
        player.stop();
    }

    private void drawScene() {
        drawField();
        progressBar.draw(this);
        roadMarking.draw(this);
        player.draw(this);
        roadManager.draw(this);
        finishLine.draw(this);
    }

    private void drawField() {
        for (int i = 0; i < WIDTH; i++)
            for (int j = 0; j < HEIGHT; j++) {
                if (j >= ROADSIDE_WIDTH && j < WIDTH - ROADSIDE_WIDTH) {
                    if (j == CENTER_X) setCellColor(j, i, Color.WHITE);
                    else setCellColor(j, i, Color.DIMGRAY);
                } else setCellColor(j, i, Color.GREEN);
            }
    }

    private void moveAll(){
        progressBar.move(roadManager.getPassedCarsCount());
        roadMarking.move(player.speed);
        player.move();
        roadManager.move(player.speed);
        finishLine.move(player.speed);
    }

    private RoadMarking roadMarking;
    private PlayerCar player;
    private RoadManager roadManager;
    private FinishLine finishLine;
    private boolean isGameStopped;
    private ProgressBar progressBar;

    private static final int RACE_GOAL_CARS_COUNT = 40;
}
