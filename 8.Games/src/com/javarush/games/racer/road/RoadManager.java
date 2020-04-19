package com.javarush.games.racer.road;

import com.javarush.engine.cell.Game;
import com.javarush.games.racer.PlayerCar;
import com.javarush.games.racer.RacerGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RoadManager {
    public static final int LEFT_BORDER = RacerGame.ROADSIDE_WIDTH;
    public static final int RIGHT_BORDER = RacerGame.WIDTH - LEFT_BORDER;


    public int getPassedCarsCount() {
        return passedCarsCount;
    }

    public void draw(Game game){
        items.forEach(item-> item.draw(game));
    }

    public void move(int boost){
        items.forEach(a-> a.move(a.speed+boost, items));
        deletePassedItems();
    }

    public void generateNewRoadObjects(Game game){
        generateThorn(game);
        generateRegularCar(game);
        generateMovingCar(game);
    }

    public boolean checkCrush(PlayerCar playerCar){
        return items.stream().anyMatch(item -> item.isCollision(playerCar));
    }

    private boolean isRoadSpaceFree(RoadObject object){
        return items.stream().noneMatch(item -> item.isCollisionWithDistance(object, PLAYER_CAR_DISTANCE));
    }

    private RoadObject createRoadObject(RoadObjectType type, int x, int y){
        switch (type) {
            case THORN:     return new Thorn(x, y);
            case DRUNK_CAR: return new MovingCar(x, y);
            default:        return new Car(type, x, y);
        }
    }

    private void addRoadObject(RoadObjectType type, Game game){
        int x = game.getRandomNumber(FIRST_LANE_POSITION, FOURTH_LANE_POSITION);
        int y = -1 * RoadObject.getHeight(type);
        RoadObject ro = createRoadObject(type, x, y);
        if(isRoadSpaceFree(ro)) items.add(ro);
    }

    private void deletePassedItems(){
        for (Iterator<RoadObject> it1 = items.iterator(); it1.hasNext(); ) {
            RoadObject it = it1.next();
            if(it.y >= RacerGame.HEIGHT) {
                if(it.type != RoadObjectType.THORN) passedCarsCount++;
                it1.remove();
            }
        }
    }

    private boolean isThornExists(){
        return items.stream().anyMatch(item->item.type == RoadObjectType.THORN);
    }

    private boolean isMovingCarExists(){
        return items.stream().anyMatch(item->item.type == RoadObjectType.DRUNK_CAR);
    }

    private void generateThorn(Game game){
        if(game.getRandomNumber(100) < 10 && !isThornExists())
            addRoadObject(RoadObjectType.THORN, game);
    }

    private void generateRegularCar(Game game){
        int carTypeNumber = game.getRandomNumber(4);
        if(game.getRandomNumber(100) < 30) addRoadObject(RoadObjectType.values()[carTypeNumber], game);
    }

    private void generateMovingCar(Game game){
        if(game.getRandomNumber(100) < 10 && !isMovingCarExists())
            addRoadObject(RoadObjectType.DRUNK_CAR, game);
    }

    private static final int FIRST_LANE_POSITION = 16;
    private static final int FOURTH_LANE_POSITION = 44;
    private static final int PLAYER_CAR_DISTANCE = 12;
    private List<RoadObject> items = new ArrayList<>();
    private int passedCarsCount = 0;
}