package com.medical.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebFilter("/*")
public class AuthFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI();

        // الصفحات المسموح بيها بدون login
        if (path.contains("login.jsp") ||
            path.contains("signup.jsp") ||
            path.contains("login") ||
            path.contains("signup") ||
            path.contains("css") ||
            path.contains("js")) {

            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("user") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        chain.doFilter(req, res);
    }
}