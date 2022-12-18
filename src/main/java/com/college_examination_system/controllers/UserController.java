package com.college_examination_system.controllers;

import com.college_examination_system.services.UserService;
import com.college_examination_system.utils.AlertHelper;
import com.college_examination_system.utils.FxmlLoader;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

public class UserController {

    @FXML
    public Button submitButton;
    @FXML
    private TextField userNameTxtField;
    @FXML
    private PasswordField passwordTxtField;
    @FXML
    private RadioButton AdminRadioBtn;

    @FXML
    private RadioButton LecturerRadioBtn;

    @FXML
    private RadioButton StudentRadioBtn;

    @FXML
    private void initialize () {
        ToggleGroup group = new ToggleGroup();
        AdminRadioBtn.setToggleGroup(group);
        AdminRadioBtn.setSelected(true);
        LecturerRadioBtn.setToggleGroup(group);
        StudentRadioBtn.setToggleGroup(group);
    }

    public void handleSubmitButtonAction() {
        Window owner = submitButton.getScene().getWindow();
        String userName = userNameTxtField.getText();
        String password = passwordTxtField.getText();

        String userRole = getUserRole();
        if (userName.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your user name");
            return;
        }
        if (password.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
            return;
        }

        if(!UserService.isUserExist(userName,password,userRole)) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "please Enter The correct Email and Password And Select The correct User Role");
       } else {
            // close login
            Stage currentStage = (Stage) this.submitButton.getScene().getWindow();
            currentStage.close();
            // here based on user role we render its view
            if ("admin".equals(userRole)) {
                FxmlLoader.load("admin-view.fxml","Admin Dashboard",1000,700, StageStyle.DECORATED);
            } else if ("student".equals(userRole)) {
                FxmlLoader.load("student-view.fxml","Student Dashboard",1000,700,StageStyle.DECORATED);
            } else if ("lecturer".equals(userRole)) {
                FxmlLoader.load("lecturer-view.fxml","Lecturer Dashboard",1000,700,StageStyle.DECORATED);
            }
        }
    }

    private String getUserRole() {
        if(AdminRadioBtn.isSelected()) {
            return AdminRadioBtn.getText().toLowerCase();
        }
        if(StudentRadioBtn.isSelected()) {
            return StudentRadioBtn.getText().toLowerCase();
        }
        if(LecturerRadioBtn.isSelected()) {
            return StudentRadioBtn.getText().toLowerCase();
        }
        return null;
    }
}

