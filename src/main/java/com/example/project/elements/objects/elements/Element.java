package com.example.project.elements.objects.elements;

public abstract class Element {

    String name;
    double square;
    double depth;

    public String getFullName() {
        if (square != 0){
            return name + "     |   Площадь загрязнения: " + square + "     Глубина загрязнения: " + depth;

        }else {
            return name + "     |   Работы не требуеются";
        }
    }

    public String getName() {
        return name;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public double getDepth() {
        return depth;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }
}
