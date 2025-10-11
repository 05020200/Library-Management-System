package com.mycompany.library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// This annotation tells Tomcat to send requests for "/signup" to this class.
@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Get data from the form
        String fullName = request.getParameter("fullName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        // 2. Use the UserDAO to create the new user
        UserDAO userDAO = new UserDAO();
        boolean wasUserCreated = userDAO.createUser(fullName, email, password);

        // 3. Redirect the user
        if (wasUserCreated) {
            // If successful, go to the login page with a success message
            response.sendRedirect("login.html?signup=success");
        } else {
            // If it fails (e.g., email already exists), go back with an error
            response.sendRedirect("login.html?error=signup_failed");
        }
    }
}
