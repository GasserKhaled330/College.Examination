package com.college_examination_system.repositories;

import com.college_examination_system.utils.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    public static boolean verifyUser(String email, String password, String userRole) {
        String tableName = getTableName(userRole);
        String query = "SELECT * FROM " + tableName + " WHERE Email='" + email + "' and password='" + password + "';";

        try {
            ResultSet resultSet = ConnectionUtil.executeQuery(query);
            if (resultSet.next()) {
                return true;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: User Not Exist " + ex);
            ex.printStackTrace();
        }
        return false;
    }

    private static String getTableName(String userRole) {
        if (userRole.equals("admin")) {
            return "Admin";
        } else if (userRole.equals("student")) {
            return "Student";
        } else {
            return "Lecturer";
        }
    }
}
