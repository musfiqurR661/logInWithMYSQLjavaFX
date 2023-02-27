module project.ourproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens project.ourproject to javafx.fxml;
    exports project.ourproject;
}