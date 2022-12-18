package com.college_examination_system.repositories;

import com.college_examination_system.models.Lecturer;
import com.college_examination_system.utils.ConnectionUtil;
import com.college_examination_system.utils.UserFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class LecturerRepository {
    public static Lecturer getLecturer(int lecturerId) {
        String query = "SELECT * FROM Lecturer INNER JOIN UserRole UR on Lecturer.roleId = UR.id WHERE id=" + lecturerId + ";";
        try {
            //Get ResultSet from ExecuteQuery method
            ResultSet rsLecturer = ConnectionUtil.executeQuery(query);
            return getLecturerDataFromResultSet(rsLecturer);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Lecturer Not Found: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Lecturer> getLecturers() {
        String query = "SELECT * FROM Lecturer INNER JOIN UserRole UR on Lecturer.roleId = UR.id;";/**/
        try {
            //Get ResultSet from ExecuteQuery method
            ResultSet rsStudents = ConnectionUtil.executeQuery(query);
            return getLecturersFromResultSet(rsStudents);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Cannot get Lecturers : " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    public static boolean createLecturer(Lecturer lecturer) {
        String insertQuery = "INSERT INTO Lecturer(name,Email,password,roleId)" +
                "Values('" + lecturer.getName() + "', '" + lecturer.getEmail() + "', '" +
                lecturer.getPassword() + "', " + 3 + ");";
        try {
            ConnectionUtil.executeUpdate(insertQuery);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
        return false;
    }

    public static boolean updateLecturer(int id, Lecturer lecturer) {
        String updateQuery = "UPDATE Lecturer SET Email='" + lecturer.getEmail() + "', password='" + lecturer.getPassword() + "' " +
                ", name='" + lecturer.getName() + "' WHERE id=" + id + ";";
        try {
            ConnectionUtil.executeUpdate(updateQuery);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
        return false;
    }

    public static boolean deleteLecturer(int lecturerId) {
        String deleteQuery = "DELETE FROM Lecturer WHERE id=" + lecturerId;
        try {
            ConnectionUtil.executeUpdate(deleteQuery);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
        return false;
    }

    private static Lecturer getLecturerDataFromResultSet(ResultSet rs) throws SQLException {
        if (rs.next()) {
            return createLecturerFromResultSet(rs);
        }
        return null;
    }
    private static ObservableList<Lecturer> getLecturersFromResultSet(ResultSet rs) throws SQLException {
        ObservableList<Lecturer> lecturers = FXCollections.observableArrayList();
        while (rs.next()) {
            Lecturer lecturer = createLecturerFromResultSet(rs);
            lecturers.add(lecturer);
        }
        return lecturers;
    }
    private static Lecturer createLecturerFromResultSet(ResultSet rs) throws SQLException {
        Lecturer lecturer = (Lecturer) UserFactory.createUser("lecturer");
        lecturer.setId(rs.getInt("id"));
        lecturer.setName(rs.getString("name"));
        lecturer.setEmail(rs.getString("Email"));
        lecturer.setPassword(rs.getString("password"));
        lecturer.setUserType(rs.getString("roleName"));
        return lecturer;
    }


}
