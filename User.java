package com.mycompany.library;

/**
 * This class is a blueprint for a User. It holds all the data for a single
 * user, like their ID, name, email, and role.
 */
public class User {

    // --- Fields ---
    private int id;
    private String name;
    private String email;
    private String role; // <-- ADDED THIS FIELD

    // --- Constructors ---
    public User() {
    }

    /**
     * Constructor to create a new User object with all its information.
     * @param id The user's unique ID.
     * @param name The user's full name.
     * @param email The user's email address.
     * @param role The user's role ('admin' or 'user').
     */
    // CONSTRUCTOR UPDATED TO INCLUDE ROLE
    public User(int id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role; // <-- ADDED THIS LINE
    }

    // --- Getters and Setters ---

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // --- ADDED THESE NEW METHODS ---
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
