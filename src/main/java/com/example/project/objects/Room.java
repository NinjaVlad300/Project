package com.example.project.objects;

import com.example.project.objects.elements.Element;
import com.example.project.objects.elements.Floor;
import com.example.project.objects.elements.Roof;
import com.example.project.objects.elements.Wall;

import java.util.Random;

public class Room {

    private int id;
    private String name;
    private String place;   // Расположение
    private Floor floor;
    private Roof roof;
    private Wall wall;
    private double power;
    private double activity;

    public Room() {
        floor = new Floor();
        roof = new Roof();
        wall = new Wall();
    }

    public String getFullName() {
        return name + " " + place;
    }

    public Element getElement(String namePart) {
        switch (namePart) {
            case "Пол" -> {
                return floor;
            }
            case "Стены" -> {
                return wall;
            }
            case "Потолок" -> {
                return roof;
            }
        }
        return null;
    }

    public double getPower() {
        Random random = new Random();
        if (place.equals("Этаж 1")) {
            return random.nextGaussian() * 5 + power;   // Случайное число, с отклонением 5% и мат ожиданием power
        } else if (place.equals("Этаж 2")) {
            return random.nextGaussian() * 10 + power;  // Случайное число, с отклонением 10% и мат ожиданием power
        } else {
            return power;
        }
    }

    public void setPower(double power) {
        this.power = power;
    }

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Floor getFloor() {
        return floor;
    }

    public Roof getRoof() {
        return roof;
    }

    public Wall getWall() {
        return wall;
    }

}
