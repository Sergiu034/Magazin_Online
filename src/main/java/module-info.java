module com.example.magazinonline {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.magazinonline to javafx.fxml;
    exports com.example.magazinonline;
    exports Controllers;
    opens Controllers to javafx.fxml;
    opens Model to javafx.base;
}