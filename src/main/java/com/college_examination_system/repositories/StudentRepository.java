package com.college_examination_system.repositories;

import com.college_examination_system.models.Student;
import com.college_examination_system.utils.ConnectionUtil;
import com.college_examination_system.utils.UserFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRepository {
    public static Student getStudent(String collegeId) {
        String query = "SELECT * FROM Student WHERE collegeId='" + collegeId+"';";
        try {
            //Get ResultSet from ExecuteQuery method
            ResultSet rsStudent = ConnectionUtil.executeQuery(query);
            return getStudentDataFromResultSet(rsStudent);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Student Not Found: " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    private static Student getStudentDataFromResultSet(ResultSet rs) throws SQLException {
      if(rs.next()){
         return createStudentFromResultSet(rs);
      }
      return null;
    }

    private static Student createStudentFromResultSet(ResultSet rs) throws SQLException {
        Student student = (Student) UserFactory.createUser("student");
        student.setId(rs.getInt("id"));
        student.setEmail(rs.getString("Email"));
        student.setPassword(rs.getString("password"));
        student.setUserType(rs.getString("roleId"));
        student.setName(rs.getString("name"));
        student.setCollegeId(rs.getString("collegeId"));
        return student;
    }

    public static ObservableList<Student> getStudents() {
        String query = "SELECT * FROM Student INNER JOIN UserRole UR on Student.roleId = UR.id;";
        try {
            //Get ResultSet from ExecuteQuery method
            ResultSet rsStudents = ConnectionUtil.executeQuery(query);
            return getStudentsFromResultSet(rsStudents);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error Cannot get Students : " + ex.getMessage());
            ex.printStackTrace();
        }
        return null;
    }

    private static ObservableList<Student> getStudentsFromResultSet(ResultSet rs) throws SQLException {
        ObservableList<Student> students = FXCollections.observableArrayList();
        while (rs.next()) {
            Student student = createStudentFromResultSet(rs);
            students.add(student);
        }
        return students;
    }

    public static boolean createStudent(Student student) {
        String insertQuery = "INSERT INTO Student(collegeId,name,userEmail,password,roleId)" +
                "Values('" + student.getCollegeId() + "', '" + student.getName() + "', '" +
                student.getEmail() + "','" + student.getPassword() + "' ," + 2 + ");";
        try {
            ConnectionUtil.executeUpdate(insertQuery);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
        return false;
    }

    public static void updateStudent(int id, Student student) {
        String updateQuery = "UPDATE Student SET Email='" + student.getEmail() + "', password='" + student.getPassword() + "' " +
                ", name='" + student.getName() + "' , collegeId='" + student.getCollegeId() +
                "' WHERE id=" + id + ";";
        try {
            ConnectionUtil.executeUpdate(updateQuery);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
    }

    public static void deleteStudent(String collegeId) {
        String deleteQuery = "DELETE FROM Student WHERE collegeId=" + collegeId;
        try {
            ConnectionUtil.executeUpdate(deleteQuery);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
    }
}
