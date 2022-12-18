package com.college_examination_system.utils;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class AlertHelper {
    public static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.initStyle(StageStyle.UTILITY);
        alert.show();
    }
}
