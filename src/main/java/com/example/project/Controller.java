package com.example.project;

import com.example.project.elements.Element;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Controller {
    ArrayList<Element> elements = new ArrayList<>();
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() throws IOException, InvalidFormatException {
        File selectedFile = new File(this.getClass().getResource("Вари2_приложение1.xlsx").getFile());

        GeneratorElement generatorElement = new GeneratorElement(selectedFile);
        for (int i=0; i<20; i++){
        elements.add(generatorElement.Create());}

        for (Element element: elements){
            System.out.println(element.getId()+" "+ element.getName()+" "+element.getDepth()+" "+element.getSquare());
        }
    }
}