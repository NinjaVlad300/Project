package com.example.project.elements.workings;

import com.example.project.dop.Storage;

public class Work {
    int idRoom;

    String namePart;    // Часть (Пол, стены, потолок)
    String nameWork;
    String deskWork;
    String type;    // Поверхностная или скол
    int priority;
    int timeTick;   // норма времени
    int numberWorkers;      // кол-во раб
    double price; // = price капитал


    public String getFullName() {
        return nameWork + "     |   Тип работы: " + type;
    }

    // норма времени
    double time = 0;

    public void setTime() {

        if (type.equals("Скол")) {
            time = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() * Math.ceil(Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getDepth() / 10) * timeTick / numberWorkers;
        } else {
            time = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() * timeTick / numberWorkers;
        }
    }

    double cost = 0;

    double kdo = 0;

    public void setCost(double costT) {
        setTime();
        if (type.equals("Скол")) {
            cost = price * Math.ceil(Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getDepth() / 10) * Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() + time * numberWorkers * costT;
        } else {
            cost = price * Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() + time * numberWorkers * costT;
        }
    }

    public double getTime() {
        return time;
    }

    public double getCost() {
        return cost;
    }

    public double getKdo() {
        return kdo;
    }

    public double getIdo() {
        return ido;
    }

    public void setKdo() {
        setTime();
        kdo = Storage.getInstance().getRoomWithId(idRoom).getPower() * time * numberWorkers;
    }

    double ido = 0;

    public void setIdo() {
        setKdo();
        ido = kdo / numberWorkers;
    }


    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNamePart() {
        return namePart;
    }

    public void setNamePart(String namePart) {
        this.namePart = namePart;
    }

    public String getNameWork() {
        return nameWork;
    }

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public String getDeskWork() {
        return deskWork;
    }

    public void setDeskWork(String deskWork) {
        this.deskWork = deskWork;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getTimeTick() {
        return timeTick;
    }

    public void setTimeTick(int timeTick) {
        this.timeTick = timeTick;
    }

    public int getNumberWorkers() {
        return numberWorkers;
    }

    public void setNumberWorkers(int numberWorkers) {
        this.numberWorkers = numberWorkers;
    }
}
