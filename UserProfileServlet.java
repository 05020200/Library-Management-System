// This code is correct! Keep it as is.
package com.mycompany.library;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/api/user/profile") // This URL is our API endpoint
public class UserProfileServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        // Check if the user is logged in
        if (session != null && session.getAttribute("loggedInUser") != null) {
            User user = (User) session.getAttribute("loggedInUser");

            // Manually create a JSON string with the user's data
            // (Note: In bigger projects, a library like Gson or Jackson is better for this)
            String userJson = "{"
                    + "\"id\": " + user.getId() + ","
                    + "\"name\": \"" + user.getName() + "\","
                    + "\"email\": \"" + user.getEmail() + "\""
                    + "}";

            // Send the JSON data back to the browser
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(userJson);

        } else {
            // If not logged in, send a "Not Authorized" error
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"error\": \"Not authorized\"}");
        }
    }
}
