package com.example.project.elements.objects.elements;

public abstract class Element {

    String name;
    double square;
    double depth;

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
