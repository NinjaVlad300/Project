module com.example.rad {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;
    requires commons.math3;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.dop;
    opens com.example.project.dop to javafx.fxml;
    exports com.example.project.objects.elements;
    opens com.example.project.objects.elements to javafx.fxml;
    exports com.example.project.objects;
    opens com.example.project.objects to javafx.fxml;
    exports com.example.project.controllers;
    opens com.example.project.controllers to javafx.fxml;
}