package com.medical.controller;

import com.medical.model.entities.User;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        EntityManager em = emf.createEntityManager();

        try {
            User user = em.createQuery(
                    "SELECT u FROM User u WHERE u.username = :u AND u.password = :p",
                    User.class
            )
            .setParameter("u", username)
            .setParameter("p", password)
            .getSingleResult();

            // ✅ login success
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("role", user.getRole());

response.sendRedirect("index.jsp");
        } catch (Exception e) {
            response.sendRedirect("login.jsp?error=1");
        }

        em.close();
    }
}