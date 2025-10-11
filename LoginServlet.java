package com.mycompany.library;

// Make sure you import the User bean
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        UserDAO userDAO = new UserDAO();
        
        // --- CHANGE #1: The DAO method now returns a User object ---
        User user = userDAO.validateUser(email, password, role);

        // --- CHANGE #2: Check if the user object is null ---
        if (user != null) {
            // Login is valid!
            HttpSession session = request.getSession();
            
            // --- CHANGE #3: Store the ENTIRE user object in the session ---
            // This is the key that your UserProfileServlet will look for.
            session.setAttribute("loggedInUser", user);

            // Your role-based redirect logic is still perfectly fine.
          if ("admin".equals(user.getRole())) {
            response.sendRedirect("admin_dash.html");
        } else if ("user".equals(user.getRole())) {
            response.sendRedirect("user_dashboard.html");
        } else {
            // Handle cases where the role is neither admin nor user
            response.sendRedirect("login.html?error=unknown_role");
        }

        } else {
            // Login failed, user is null
            response.sendRedirect("login.html?error=invalid_credentials");
        }
    }
}
