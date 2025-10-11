package com.mycompany.library;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    /**
     * Checks the database to see if a user exists with the given credentials and role.
     * @param email The user's email.
     * @param password The user's password.
     * @param role The user's role ('admin' or 'user').
     * @return true if a match is found, false otherwise.
     */
// --- FIND THIS METHOD ---
// public boolean validateUser(String email, String password, String role) { ... }

// --- AND REPLACE IT WITH THIS ---
public User validateUser(String email, String password, String role) {
    User user = null;
    // Assuming you have a DBConnection class
    try (Connection connection = DBConnection.getConnection()) {
        String sql = "SELECT * FROM members WHERE email = ? AND password = ? AND role = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, email);
        ps.setString(2, password); // Note: In a real app, passwords should be hashed!
        ps.setString(3, role);

        ResultSet rs = ps.executeQuery();

        // If a matching user is found in the database...
 // In UserDAO.java
if (rs.next()) {
    int id = rs.getInt("id"); 
    String name = rs.getString("name");
    String userEmail = rs.getString("email");
    String userRole = rs.getString("role"); // Get the role from the database

    // Pass the role to the constructor
    user = new User(id, name, userEmail, userRole); 
}
    } catch (SQLException e) {
        e.printStackTrace(); // Handle exceptions properly
    }
    
    // Return the full User object, or null if not found
    return user; 
}

    /**
     * Adds a new user to the members table with a default 'user' role.
     * @param name The new user's full name.
     * @param email The new user's email.
     * @param password The new user's password.
     * @return true if the user was added successfully, false otherwise.
     */
    public boolean createUser(String name, String email, String password) {
        String sql = "INSERT INTO members(name, email, password, role) VALUES(?, ?, ?, 'user')";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, password);
            
            int rowsAffected = pstmt.executeUpdate();
            
            return rowsAffected > 0;
            
        } catch (SQLException e) {
            System.err.println("Error creating user: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // As your project grows, you would add more methods here, like:
    // public void updateUserPassword(int userId, String newPassword) { ... }
    // public void deleteUser(int userId) { ... }
}
