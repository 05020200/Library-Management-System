package com.mycompany.library;

// In your UserAuthFilter.java file

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// This filter should ONLY protect the user dashboard URL
@WebFilter("/user_dashboard.html") 
public class UserAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpSession session = httpRequest.getSession(false);

        // 1. Check if a user is logged in at all
        boolean isLoggedIn = (session != null && session.getAttribute("loggedInUser") != null);
        
        boolean isCorrectRole = false;

        if (isLoggedIn) {
            // 2. If logged in, get the User object
            User user = (User) session.getAttribute("loggedInUser");
            
            // 3. CRITICAL: Check if that user's role is specifically "user"
            if ("user".equals(user.getRole())) {
                isCorrectRole = true;
            }
        }

        if (isCorrectRole) {
            // If they are logged in AND have the 'user' role, let them proceed.
            chain.doFilter(request, response);
        } else {
            // Otherwise, send them back to the login page.
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
        }
    }
}
