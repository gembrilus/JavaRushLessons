package com.javarush.task.task05.task0522;

/* 
Максимум конструкторов
*/

public class Circle {
    public double x;
    public double y;
    public double radius;

    public Circle(){
        this.x = 0;
        this.y = 0;
        this.radius = 10;
    }

    public Circle(int x, int y){
        this.x = x;
        this.y = y;
        this.radius = 10;
    }

    public Circle(int x, int y, int radius){
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public Circle(int radius){
        this.x = 0;
        this.y = 0;
        this.radius = radius;
    }

    public Circle(Circle circle){
        this.x = circle.x;
        this.y = circle.y;
        this.radius = circle.radius;
    }

    public static void main(String[] args) {

    }
}