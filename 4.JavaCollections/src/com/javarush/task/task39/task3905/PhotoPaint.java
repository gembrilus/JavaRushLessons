package com.javarush.task.task39.task3905;

public class PhotoPaint {
    public boolean paintFill(Color[][] image, int x, int y, Color desiredColor) {
        if (y >=0 && y < image.length && x >= 0 && x < image[y].length && image[y][x] != desiredColor){
            image[y][x] = desiredColor;
            return true;
        }
        return false;
    }
}
