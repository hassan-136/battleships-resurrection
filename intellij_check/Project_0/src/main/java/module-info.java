module com.example.project_0 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.media;
    requires java.desktop;
    opens com.example.project_0 to javafx.fxml;
    exports com.example.project_0;
}
