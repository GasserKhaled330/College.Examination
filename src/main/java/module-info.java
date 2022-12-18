module com.college_examination_system {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.j;
    requires java.sql.rowset;

    opens com.college_examination_system to javafx.fxml;
    exports com.college_examination_system;
    opens com.college_examination_system.controllers to javafx.fxml;
    exports com.college_examination_system.controllers;
}