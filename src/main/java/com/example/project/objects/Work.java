package com.example.project.objects;

import com.example.project.dop.Storage;
import com.example.project.objects.elements.Element;

public class Work {
    int idRoom;
    String namePart;    // Часть (Пол, стены, потолок)
    String nameWork;
    String deskWork;
    String type;        // Поверхностная или скол
    int priority;
    int timeTick;       // норма времени
    int numberWorkers;  // кол-во рабочих
    double price;       // = price капитальная
    double time = 0;
    double cost = 0;
    double cdo = 0;
    double ido = 0;

    public String getFullName() {
        return nameWork + "     |   Тип работы: " + type;
    }

    public String getNameWork() {
        return nameWork;
    }

    public String getType() {
        return type;
    }

    public int getPriority() {
        return priority;
    }

    public int getTimeTick() {
        return timeTick;
    }

    public int getNumberWorkers() {
        return numberWorkers;
    }

    public double getPrice() {
        return price;
    }

    public void setTime() {
        Element element = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart);
        if (type.equals("Скол")) {
            time = element.getSquare() * timeTick / numberWorkers * Math.ceil(element.getDepth() / 10);
        } else {
            time = element.getSquare() * timeTick / numberWorkers;
        }
    }

    public void setKdo() {
        setTime();
        cdo = Storage.getInstance().getRoomWithId(idRoom).getPower() * time * numberWorkers;
    }

    public void setIdo() {
        setKdo();
        ido = cdo / numberWorkers;
    }

    public void setCost(double costT) {
        Element element = Storage.getInstance().getRoomWithId(idRoom).getElement(namePart);
        setTime();
        if (type.equals("Скол")) {
            cost = price * element.getSquare() + time * numberWorkers * costT * Math.ceil(element.getDepth() / 10) ;
        } else {
            cost = price * element.getSquare() + time * numberWorkers * costT;
        }
    }

    public double getTime() {
        return time;
    }

    public double getCost() {
        return cost;
    }

    public double getCdo() {
        return cdo;
    }

    public double getIdo() {
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

    public void setNameWork(String nameWork) {
        this.nameWork = nameWork;
    }

    public String getDeskWork() {
        return deskWork;
    }

    public void setDeskWork(String deskWork) {
        this.deskWork = deskWork;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setTimeTick(int timeTick) {
        this.timeTick = timeTick;
    }

    public void setNumberWorkers(int numberWorkers) {
        this.numberWorkers = numberWorkers;
    }

}
