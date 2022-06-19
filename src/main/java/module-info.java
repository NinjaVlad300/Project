module com.example.rad {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.poi.poi;
    requires org.apache.poi.ooxml;


    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.dop;
    opens com.example.project.dop to javafx.fxml;
}