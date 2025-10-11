package com.mycompany.library;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // This path points to the database file inside your project folder
    private static final String DB_URL = "jdbc:sqlite:C:/Users/Divya/OneDrive/Documents/NetBeansProjects/Library/lib-1.db";

    public static Connection getConnection() {
        try {
            Class.forName("org.sqlite.JDBC");
            return DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("DATABASE CONNECTION FAILED. Here is the reason:");
            e.printStackTrace();
            return null; 
        }
    }
}
