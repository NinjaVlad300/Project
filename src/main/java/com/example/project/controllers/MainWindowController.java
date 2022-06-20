package com.example.project.controllers;

import com.example.project.Application;
import com.example.project.dop.Calculator;
import com.example.project.dop.Storage;
import com.example.project.dop.Import;
import com.example.project.objects.Room;
import com.example.project.objects.Work;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class MainWindowController {

    @FXML
    private TextField costField;

    @FXML
    private TextField idoField;

    @FXML
    private TextField kdoField;

    @FXML
    private TextField timeField;

    @FXML
    private TextField priceField;

    @FXML
    private TreeView<String> tree;

    Import imp = new Import();

    Calculator calculator = new Calculator();

    @FXML
    void startRandCol(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("col_view.fxml"));
            Scene Scene = new Scene(fxmlLoader.load());
            Stage orderStage = new Stage();
            orderStage.setTitle("Коллективная эквивалентная доза");
            orderStage.setScene(Scene);
            orderStage.initModality(Modality.APPLICATION_MODAL);
            orderStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void startRandInd(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("ind_view.fxml"));
            Scene Scene = new Scene(fxmlLoader.load());
            Stage orderStage = new Stage();
            orderStage.setTitle("Индивидуальная эквивалентная доза");
            orderStage.setScene(Scene);
            orderStage.initModality(Modality.APPLICATION_MODAL);
            orderStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void error(String e) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeight(400);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(e);
        alert.showAndWait();
    }

    void info(String e) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setHeight(400);
//        alert.setTitle("Info");
//        alert.setHeaderText(null);
//        alert.setContentText(e);
//        alert.showAndWait();
    }

    public void initialize() {

        idoField.setEditable(false);
        kdoField.setEditable(false);
        timeField.setEditable(false);
        costField.setEditable(false);


        try {
            InputStream isObject = (Application.class.getResourceAsStream("1.xlsx"));
            imp.impObject(imp.createWorkbook(isObject));


            InputStream isWorks = (Application.class.getResourceAsStream("2.xlsx"));
            imp.impWorks(imp.createWorkbook(isWorks));

            initializeTree();

        } catch (Exception e) {
            e.printStackTrace();
            error("Ошибка " + e.getMessage());
        }


        info("Все данные загружены");

    }

    @FXML
    void chooseFile(ActionEvent event) throws IOException, InvalidFormatException {
        try {
            FileChooser fc = new FileChooser();
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Excel", "*.xlsx");
            fc.getExtensionFilters().add(extFilter);
            File file = fc.showOpenDialog(null);

            Storage.getInstance().getWorks().clear();

            imp.impWorks(imp.createWorkbook(file));

            if (Storage.getInstance().getWorks().size() == 0) {
                InputStream isWorks = (Application.class.getResourceAsStream("2.xlsx"));
                imp.impWorks(imp.createWorkbook(isWorks));
                error("Ошибка ");
            } else {
                info("Файл успешно загружен");
            }

        } catch (Exception e) {
            InputStream isWorks = (Application.class.getResourceAsStream("2.xlsx"));
            imp.impWorks(imp.createWorkbook(isWorks));
            error("Ошибка "+ e.getMessage());
            e.printStackTrace();
        }
        initializeTree();
    }

    public void initializeTree() {
        TreeItem<String> root = new TreeItem<>();

        for (Room room : Storage.getInstance().getRooms()) {


            TreeItem<String> roomItem = new TreeItem<>(room.getFullName());
            root.getChildren().add(roomItem);
            roomItem.setExpanded(true);

            TreeItem<String> floorItem = new TreeItem<>(room.getFloor().getFullName());
            roomItem.getChildren().add(floorItem);

            TreeItem<String> roofItem = new TreeItem<>(room.getRoof().getFullName());
            roomItem.getChildren().add(roofItem);

            TreeItem<String> wallItem = new TreeItem<>(room.getWall().getFullName());
            roomItem.getChildren().add(wallItem);


            for (Work work : Storage.getInstance().getWorks()) {

                if (work.getIdRoom() == room.getId()) {

                    TreeItem<String> workItem = new TreeItem<>();
                    switch (work.getNamePart()) {
                        case "Потолок" -> {
                            workItem.setValue(work.getFullName());
                            roofItem.getChildren().add(workItem);
                        }
                        case "Пол" -> {
                            workItem.setValue(work.getFullName());
                            floorItem.getChildren().add(workItem);
                        }
                        case "Стены" -> {
                            workItem.setValue(work.getFullName());
                            wallItem.getChildren().add(workItem);
                        }
                    }

                    TreeItem<String> descItem = new TreeItem<>(work.getDeskWork());
                    workItem.getChildren().add(descItem);


                }
            }

        }

        tree.setRoot(root);
        tree.setShowRoot(false);
    }

    @FXML
    void costProject(ActionEvent event) {
        costField.setText(String.format("%.3f", calculator.calcCost(Double.parseDouble(priceField.getText()))));
    }

    @FXML
    void idoProject(ActionEvent event) {
        idoField.setText(String.format("%.3f", calculator.calcIdo()));
    }

    @FXML
    void kdoProject(ActionEvent event) {
        kdoField.setText(String.format("%.3f", calculator.calcKdo()));
    }

    @FXML
    void timeProject(ActionEvent event) {
        timeField.setText(String.format("%.3f", calculator.calcTime()));
    }

}