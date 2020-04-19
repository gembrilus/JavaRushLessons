package com.javarush.task.task15.task1522;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static com.javarush.task.task15.task1522.Planet.*;

/* 
Закрепляем паттерн Singleton
*/

public class Solution {
    public static void main(String[] args) {
    }

    public static Planet thePlanet;

    static {readKeyFromConsoleAndInitPlanet();}

    public static void readKeyFromConsoleAndInitPlanet() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String s = "";
        try{
            s = reader.readLine();
        }catch (IOException e){
            try {
                reader.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        switch (s){
            case SUN: thePlanet = Sun.getInstance(); break;
            case MOON: thePlanet = Moon.getInstance(); break;
            case EARTH: thePlanet = Earth.getInstance(); break;
            default: thePlanet = null;
        }
    }
}
