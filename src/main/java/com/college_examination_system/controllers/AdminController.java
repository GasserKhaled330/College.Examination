package com.college_examination_system.controllers;

import com.college_examination_system.models.Lecturer;
import com.college_examination_system.models.Student;
import com.college_examination_system.models.User;
import com.college_examination_system.services.AdminService;
import com.college_examination_system.services.LecturerService;
import com.college_examination_system.services.StudentService;
import com.college_examination_system.services.UserService;
import com.college_examination_system.utils.AlertHelper;
import com.college_examination_system.utils.FxmlLoader;
import com.college_examination_system.utils.UserFactory;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Callback;

public class AdminController {

    // Buttons
    @FXML
    private Button UpdateAdminBtn;
    @FXML
    private Button btnAdminProfile;

    @FXML
    private Button btnCourses;

    @FXML
    private Button btnLectures;

    @FXML
    private Button btnStudents;
    @FXML
    private Button btnLogout;
    @FXML
    private Button addStudentBtn;

    @FXML
    private Button addLecturerBtn;

    // admin dashboard panes
    @FXML
    private GridPane coursesGridPane;
    @FXML
    private GridPane lecturersGridPane;

    @FXML

    private Pane adminProfilePane;

    @FXML
    private GridPane studentsGridPane;

    // table and its columns
    @FXML
    private TableColumn<Student, Integer> studentIdCol;
    @FXML
    private TableColumn<Student, String> studentCollegeIdCol;
    @FXML
    private TableColumn<Student, String> studentNameCol;
    @FXML
    private TableColumn<Student, String> studentEmailCol;

    @FXML
    private TableColumn<Student, String> studentPasswordCol;
    @FXML
    private TableColumn<Student, String> deleteStudentActionCol;
    @FXML
    private TableView<Student> studentTable;

    // lecturer table and its column
    @FXML
    private TableView<Lecturer> lecturerTable;
    @FXML
    private TableColumn<Lecturer, Integer> lecturerIdCol;

    @FXML
    private TableColumn<Lecturer, String> lecturerNameCol;
    @FXML
    private TableColumn<Lecturer, String> lecturerEmailCol;
    @FXML
    private TableColumn<Lecturer, String> lecturerPasswordCol;

    @FXML
    private TableColumn<Lecturer, String> deleteLecturerActionCol;

    ////////////
    // text fields
    ////////////
    @FXML
    private TextField adminEmailTxtField;

    @FXML
    private TextField adminIdTxtField;

    @FXML
    private TextField adminPasswordTxtField;
    @FXML
    private TextField studentEmailTxtField;
    @FXML
    private TextField studentNameTxtField;
    @FXML
    private TextField studentPasswordTxtField;

    @FXML
    private TextField collegeIdTxtField;

    @FXML
    private TextField lecturerNameTxtField;

    @FXML
    private TextField lecturerEmailTxtField;

    @FXML
    private TextField lecturerPasswordTxtField;

    @FXML
    private void initialize() {
        buildStudentTable();
        buildLecturerTable();
        makeTablesEditable();
    }

    // switch between panes
    @FXML
    private void handleClicks() {
        btnAdminProfile.setOnMouseClicked(e -> adminProfilePane.toFront());
        btnStudents.setOnMouseClicked(e -> studentsGridPane.toFront());
        btnLectures.setOnMouseClicked(e -> lecturersGridPane.toFront());
        btnCourses.setOnMouseClicked(e -> coursesGridPane.toFront());
    }

    // Admin operations
    @FXML
    void onShowAdminDataBtnClicked(ActionEvent event) {
        User adminData = AdminService.getAdminData();
        adminIdTxtField.setText(String.valueOf(adminData.getId()));
        adminEmailTxtField.setText(adminData.getEmail());
        adminPasswordTxtField.setText(adminData.getPassword());
    }

    @FXML
    void onUpdateAdminBtnClicked(MouseEvent mouseEvent) {
        Window owner = UpdateAdminBtn.getScene().getWindow();
        int id = Integer.parseInt(adminIdTxtField.getText());
        String email = adminEmailTxtField.getText();
        String password = adminPasswordTxtField.getText();
        if (email.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter your Email address");
            return;
        }
        if (password.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.ERROR, owner, "Form Error!", "Please enter a password");
            return;
        }

        boolean result = AdminService.updateAdmin(id, email, password);
        if (result) {
            AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Update success", "Your Account Data Updated Successfully");
        }
    }

    @FXML
    void onLogoutMouseClicked() {
        // close Admin dashboard window
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();
        String fxmlFile = "login-view.fxml";
        FxmlLoader.load(fxmlFile, 650, 400, "User Log in");
    }

    @FXML
    void onAddLecturerBtnClicked(ActionEvent event) {
        Window owner = addLecturerBtn.getScene().getWindow();
        String lecturerName = lecturerNameTxtField.getText();
        String lecturerEmail = lecturerEmailTxtField.getText();
        String lecturerPassword = lecturerPasswordTxtField.getText();
        if (lecturerName.isEmpty() && lecturerEmail.isEmpty() && lecturerPassword.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Add Lecturer Failed!", "Please All Text Fields Should not be Empty");
        }
        if (!UserService.isUserExist(lecturerEmail, lecturerEmail, "lecturer")) {
            Lecturer lecturer = (Lecturer) UserFactory.createUser("lecturer");
            lecturer.setName(lecturerName);
            lecturer.setEmail(lecturerEmail);
            lecturer.setPassword(lecturerPassword);
            lecturer.setUserType("lecturer");
            if (LecturerService.createLecturer(lecturer)) {
                AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Add Lecturer Success!", "Lecturer Added Successfully");
                populateLecturers(LecturerService.getLecturers());
            }
            lecturerNameTxtField.clear();
            lecturerEmailTxtField.clear();
            lecturerPasswordTxtField.clear();
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Add Lecturer Failed!", "Email Already Exist");
        }

    }

    // Student operations
    @FXML
    void onAddStudentBtnClicked(ActionEvent event) {
        Window owner = addStudentBtn.getScene().getWindow();
        String collegeId = collegeIdTxtField.getText();
        String studentName = studentNameTxtField.getText();
        String studentEmail = studentEmailTxtField.getText();
        String studentPassword = studentPasswordTxtField.getText();

        if (collegeId.isEmpty() && studentName.isEmpty() && studentEmail.isEmpty() && studentPassword.isEmpty()) {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Add Student Failed!", "Please All Text Fields Should not be Empty");
        }
        if (StudentService.isStudentExist(collegeId)) {
            if (!UserService.isUserExist(studentEmail, studentPassword, "student")) {
                Student student = (Student) UserFactory.createUser("student");
                student.setEmail(studentEmail);
                student.setPassword(studentPassword);
                student.setCollegeId(collegeId);
                student.setName(studentName);
                student.setUserType("student");
                if (StudentService.createStudent(student)) {
                    AlertHelper.showAlert(Alert.AlertType.CONFIRMATION, owner, "Add Student Success!", "Student Added Successfully");
                    //populateStudents(StudentService.getStudents());
                    studentTable.refresh();
                }
                collegeIdTxtField.clear();
                studentNameTxtField.clear();
                studentEmailTxtField.clear();
                studentPasswordTxtField.clear();
            } else {
                AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Add Student Failed!", "Email Already Exist");
            }
        } else {
            AlertHelper.showAlert(Alert.AlertType.WARNING, owner, "Add Student Failed!", "This Student Already Exist");
        }
    }

    private void buildStudentTable() {
        initializeStudentTableCols();
        populateStudents(StudentService.getStudents());
    }

    private void buildLecturerTable() {
        initializeLecturerTableCols();
        populateLecturers(LecturerService.getLecturers());
    }

    private void initializeLecturerTableCols() {
        lecturerIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        lecturerNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        lecturerEmailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        lecturerPasswordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        // adding delete button to row if contain data
        Callback<TableColumn<Lecturer, String>, TableCell<Lecturer, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Lecturer, String> call(final TableColumn<Lecturer, String> Col) {
                return new TableCell<>() {
                    final Button deleteStudentBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setDeleteButtonStyle(deleteStudentBtn);
                            deleteStudentBtn.setOnAction(event -> {
                                Lecturer lecturer = getTableView().getItems().get(getIndex());
                                LecturerService.deleteLecturer(lecturer.getId());
                                getTableView().getItems().remove(getIndex());
                            });
                            setGraphic(deleteStudentBtn);
                        }
                    }
                };
            }
        };

        deleteLecturerActionCol.setCellFactory(cellFactory);
    }

    private void initializeStudentTableCols() {
        studentIdCol.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        studentCollegeIdCol.setCellValueFactory(cellData -> cellData.getValue().collegeIdProperty());
        studentNameCol.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        studentEmailCol.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        studentPasswordCol.setCellValueFactory(cellData -> cellData.getValue().passwordProperty());
        // adding delete button to row if contain data
        Callback<TableColumn<Student, String>, TableCell<Student, String>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, String> call(final TableColumn<Student, String> Col) {
                return new TableCell<>() {
                    final Button deleteStudentBtn = new Button("Delete");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            setDeleteButtonStyle(deleteStudentBtn);
                            deleteStudentBtn.setOnAction(event -> {
                                Student student = getTableView().getItems().get(getIndex());
                                StudentService.deleteStudent(student.getCollegeId());
                                getTableView().getItems().remove(getIndex());
                            });
                            setGraphic(deleteStudentBtn);
                        }
                    }
                };
            }
        };

        deleteStudentActionCol.setCellFactory(cellFactory);
    }

    private void makeTablesEditable() {

        studentCollegeIdCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentCollegeIdCol.setOnEditCommit(e -> {
            e.getRowValue().setCollegeId(e.getNewValue());
            StudentService.updateStudent(e.getRowValue().getId(), e.getRowValue());
        });

        studentNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentNameCol.setOnEditCommit(e -> {
            e.getRowValue().setName(e.getNewValue());
            StudentService.updateStudent(e.getRowValue().getId(), e.getRowValue());
        });

        studentEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentEmailCol.setOnEditCommit(e -> {
            e.getRowValue().setEmail(e.getNewValue());
            StudentService.updateStudent(e.getRowValue().getId(), e.getRowValue());
        });

        studentPasswordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        studentPasswordCol.setOnEditCommit(e -> {
            e.getRowValue().setPassword(e.getNewValue());
            StudentService.updateStudent(e.getRowValue().getId(), e.getRowValue());
        });

        lecturerNameCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturerNameCol.setOnEditCommit(e -> {
            e.getRowValue().setName(e.getNewValue());
            LecturerService.updateLecturer(e.getRowValue().getId(), e.getRowValue());
        });

        lecturerEmailCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturerEmailCol.setOnEditCommit(e -> {
            e.getRowValue().setEmail(e.getNewValue());
            LecturerService.updateLecturer(e.getRowValue().getId(), e.getRowValue());
        });

        lecturerPasswordCol.setCellFactory(TextFieldTableCell.forTableColumn());
        lecturerPasswordCol.setOnEditCommit(e -> {
            e.getRowValue().setPassword(e.getNewValue());
            LecturerService.updateLecturer(e.getRowValue().getId(), e.getRowValue());
        });
        studentTable.setEditable(true);
        lecturerTable.setEditable(true);
    }

    private void setDeleteButtonStyle(Button btn) {
        btn.setBackground(Background.fill(Color.RED));
        btn.setMinWidth(90);
        btn.setMinHeight(20);
        btn.setAlignment(Pos.CENTER);
        btn.setCursor(Cursor.HAND);
        btn.setTextFill(Color.WHEAT);
        btn.setFont(new Font("Arial", 14));
    }

    //Populate Students for TableView
    @FXML
    private void populateStudents(ObservableList<Student> studentData) {
        //Set items to the studentTable
        studentTable.setItems(studentData);
    }

    //Populate Lecturers for TableView
    private void populateLecturers(ObservableList<Lecturer> lecturerData) {
        lecturerTable.setItems(lecturerData);
    }
}
