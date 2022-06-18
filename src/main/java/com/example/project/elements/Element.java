package com.example.project.elements;

import jdk.internal.icu.text.UnicodeSet;

public abstract class Element {
    int depth;
    String square;
    String name;
    int Id;

    public Element(int depth, String square){
        this.depth=depth;
        this.square=square;
    }

    public void setDepth(String depth){
        this.depth=depth;
    }

    public String getSquare(){
        return square;
    }

    public void setSquare(String square){
        this.square=square;
    }

    public int getDepth(){
        return depth;
    }

    public void  setName(String name){
        this.name=name;
    }

   public String getName(){
        return name;
   }

   public int getId(){
        return Id;
   }
   public void setId(int Id){
        this.Id =Id;
   }

}
