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

    public void setActivity(double activity) {
        this.activity = activity;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public String getFullName() {
        return name + " " + place;
    }

    public Element getElement(String namePart) {
        Element element = null;
        switch (namePart) {
            case "Пол" -> element = floor;
            case "Стены" -> element = wall;
            case "Потолок" -> element = roof;
        }
        return element;
    }

    public double getPower() {
        Random random = new Random();
        double powerRand = this.power;
        if (place.equals("Этаж 1")) {
            powerRand = random.nextGaussian()*5 + power;
        }else if (place.equals("Этаж 2")) {
            powerRand = random.nextGaussian()*10 + power;
        }
        return powerRand;
    }

    public double getActivity() {
        return activity;
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Roof getRoof() {
        return roof;
    }

    public void setRoof(Roof roof) {
        this.roof = roof;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }
}
