package com.mycompany.library;

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

// This annotation tells the server to run this filter for any request to dashboard.html
@WebFilter("/dashboard.html")
public class Authfilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session = req.getSession(false); // Get session if it exists, don't create a new one

        // Check if a session exists AND if the userRole attribute is "admin"
        if (session != null && "admin".equals(session.getAttribute("userRole"))) {
            // If yes, the user is an authorized admin. Allow the request to proceed.
            chain.doFilter(request, response);
        } else {
            // If no, the user is not an admin or is not logged in.
            // Redirect them to the login page.
            res.sendRedirect("login.html");
        }
    }
}
