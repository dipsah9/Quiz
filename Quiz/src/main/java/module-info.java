module com.dip.quiz {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;

    // requires mysql.connector.java;
    opens com.dip.quiz to javafx.fxml;
    exports com.dip.quiz;
}