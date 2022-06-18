package com.example.project.elements.workings;

import com.example.project.Storage;

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


    double priceInt = 10;  /// !!!!!!! Поулчать из интерфейса


    // норма времени
    double time = 0;
    public double getTime() {
        if (time == 0) {
            if (type.equals("Скол")) {
                time = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() * Math.ceil(Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getDepth() / 10) * timeTick / numberWorkers;
            } else {
                time = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() * timeTick / numberWorkers;
            }
        }
        return time;
    }

    double cost = 0;
    public double getCost() {
        if (cost == 0) {
            if (type.equals("Скол")) {
                cost = price * Math.ceil(Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getDepth() / 10) * Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() + getTime() * numberWorkers * priceInt;
            } else {
                cost = price * Storage.getInstance().getRoomWithId(idRoom).getElement(namePart).getSquare() + getTime() * numberWorkers * priceInt;
            }
        }
        return cost;
    }

    double kdo = 0;
    public double getKdo() {
        if (kdo == 0) {
            kdo = Storage.getInstance().getRoomWithId(idRoom).getPower() * getTime() * numberWorkers;
        }
        return kdo;
    }

    double ido = 0;
    public double getIdo() {
        if (ido == 0) {
            ido = getKdo() / numberWorkers;
        }
        return ido;
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
