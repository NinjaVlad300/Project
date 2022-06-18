module com.example.project {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires org.apache.poi.ooxml;

    opens com.example.project to javafx.fxml;
    exports com.example.project;
    exports com.example.project.elements;
    opens com.example.project.elements to javafx.fxml;
    exports com.example.project.workers;
    opens com.example.project.workers to javafx.fxml;
}