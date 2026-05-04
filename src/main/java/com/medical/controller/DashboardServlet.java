package com.medical.controller;

import com.medical.model.entities.*;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {

            long patients = em.createQuery("SELECT COUNT(p) FROM Patient p", Long.class).getSingleResult();
            long staff = em.createQuery("SELECT COUNT(s) FROM Staff s", Long.class).getSingleResult();
            long appointments = em.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).getSingleResult();
            long equipment = em.createQuery("SELECT COUNT(e) FROM Equipment e", Long.class).getSingleResult();

            request.setAttribute("patientsCount", patients);
            request.setAttribute("staffCount", staff);
            request.setAttribute("appointmentsCount", appointments);
            request.setAttribute("equipmentCount", equipment);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }
}