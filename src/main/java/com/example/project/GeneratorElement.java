package com.example.project;

import com.example.project.elements.Ceiling;
import com.example.project.elements.Element;
import com.example.project.elements.Floor;
import com.example.project.elements.Walls;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

public class GeneratorElement {
    private Reader reader;

    private String[] depth;
    private String[] square;


    public String[] getDepth(){return  depth;}
    public String[] getSquare(){return square;}

    public GeneratorElement(File file) throws IOException, InvalidFormatException {
        reader = new Reader(file);
    }

    public void setUp(int i){
        depth = reader.readData(1,i+1);
        square = reader.readData(1,i);
    }

    public Element Create(){
        Element element=null;
        if (element instanceof Floor){
            setUp(18);
            element.getSquare.add(square);
            element.setDepth(depth);
        } else if (element instanceof Walls){
            setUp(20);
            element.setSquare(square);
            element.setDepth(depth);}
        else if (element instanceof Ceiling){
            setUp(22);
            element.setSquare(square);
            element.setDepth(depth);}

        return element;
    }
}
