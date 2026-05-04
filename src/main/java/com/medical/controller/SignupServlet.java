package com.medical.controller;

import com.medical.model.entities.User;
import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User u = new User();
            u.setUsername(request.getParameter("username"));
            u.setPassword(request.getParameter("password"));

            // 👇 default role
            u.setRole("staff");

            em.persist(u);

            em.getTransaction().commit();

            response.sendRedirect("login.jsp");

        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();
    }
}