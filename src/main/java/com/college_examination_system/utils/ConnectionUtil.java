package com.college_examination_system.utils;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class ConnectionUtil {
    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static Connection DBConnection = null;

    //Connection String
    //String connectionStr = "jdbc:mysql://Host:PORT/DatabaseName/USERNAME/PASSWORD";
    //Username=admin, Password=admin@123, Host=localhost, IP=3306
    private static final String connectionStr = "jdbc:mysql://localhost:3306/college_examination";
    // connect to mysql database
    public static void connectToDB() throws SQLException, ClassNotFoundException{
        //Setting MYSQL JDBC Driver
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MYSQL JDBC Driver?");
            e.printStackTrace();
            throw e;
        }

        System.out.println("MYSQL JDBC Driver Registered!");

        //Establish the Oracle Connection using Connection String
        try {
            DBConnection = DriverManager.getConnection(connectionStr,"admin","admin@123");
        } catch (SQLException e) {
            System.out.println("Error: Connection Failed " + e);
            e.printStackTrace();
            throw e;
        }
    }

    //Close Connection
    public static void dispose() throws SQLException {
        if (DBConnection != null && !DBConnection.isClosed()) {
            DBConnection.close();
        }
    }

    // DB Execute Query SELECT Operation
    public static ResultSet executeQuery(String query) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        try{
            connectToDB();
            System.out.println("Select statement: " + query + "\n");

            stmt = DBConnection.prepareStatement(query);

            resultSet = stmt.executeQuery();
            // CachedRowSet Implementation
            // In order to prevent "java.sql.SQLRecoverableException: Closed Connection" error
            // use CachedRowSet
            crs = RowSetProvider.newFactory().createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException ex) {
            System.out.println("Problem occurred at executeQuery operation : " + ex);
            throw ex;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            dispose();
        }
        return crs;
    }

    // DB Execute UPDATE Queries (FOR insert,update,delete)
    public static void executeUpdate(String query) throws SQLException, ClassNotFoundException{
        PreparedStatement stmt = null;
        try {
            connectToDB();
            System.out.println("Update statement: " + query + "\n");
            stmt = DBConnection.prepareStatement(query);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Failed at executeUpdate operation: " + ex);
            throw ex;
        } finally {
            if (stmt != null) {
                //Close statement
                stmt.close();
            }
            //Close connection
            dispose();
        }
    }

}
