package com.example.project;

import com.example.project.elements.workings.Work;
import com.example.project.parsers.Import;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main_window.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Radiation");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, InvalidFormatException {
        //launch();

        File file = new File(Application.class.getResource("1.xlsx").getFile());

        Import imp = new Import();

        imp.impObject(file);

        var rooms = Storage.getInstance().getRooms();


        File file2 = new File(Application.class.getResource("2.xlsx").getFile());


        imp.impWorks(file2);

        var works = Storage.getInstance().getWorks();


        double time = 0;
        double cost = 0;
        double kdo = 0;
        double ido = 0;

        for (Work work: Storage.getInstance().getWorks()) {
            time += work.getTime();
            cost += work.getCost();
            kdo += work.getKdo();
            ido += work.getIdo();
        }

        ido = ido / Storage.getInstance().getWorks().size();

        System.out.println(time);
        System.out.println(cost);
        System.out.println(kdo);
        System.out.println(ido);

    }
}