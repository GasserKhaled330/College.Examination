package com.college_examination_system.repositories;

import com.college_examination_system.models.User;
import com.college_examination_system.utils.ConnectionUtil;
import com.college_examination_system.utils.UserFactory;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepository {

    public static User getAdminData() {
        String query = "SELECT * FROM Admin INNER JOIN UserRole UR on Admin.roleId = UR.id;";

        try {
            ResultSet resultSet = ConnectionUtil.executeQuery(query);
            if (resultSet.next()) {
                User user = UserFactory.createUser("admin");
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("Email"));
                user.setPassword(resultSet.getString("password"));
                user.setUserType(resultSet.getString("roleName"));
                return user;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: User Not Exist" + ex);
        }
        return null;
    }

    public static boolean updateAdmin(int id, String email, String password) {
        String updateQuery = "UPDATE Admin SET Email='" + email + "', password='" + password + "' " +
                " WHERE id=" + id;
        try {
            ConnectionUtil.executeUpdate(updateQuery);
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println("Error: Failed to Update your Account " + ex);
        }
        return false;
    }

}
