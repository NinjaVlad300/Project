package com.example.project;

import com.example.project.elements.objects.Room;
import com.example.project.elements.workings.Work;

import java.util.ArrayList;

public class Storage {

    private static Storage instance;
    private ArrayList<Room> rooms;
    private ArrayList<Work> works;

    private Storage() {
        rooms = new ArrayList<>();
        works = new ArrayList<>();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }
        return instance;
    }

    public static void setInstance(Storage instance) {
        Storage.instance = instance;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<Work> getWorks() {
        return works;
    }

    public void setWorks(ArrayList<Work> works) {
        this.works = works;
    }

    public Room getRoomWithId(int id) {
        for (Room room: rooms) {
            if (id == room.getId()){
                return room;
            }
        }
        return null;
    }

}
