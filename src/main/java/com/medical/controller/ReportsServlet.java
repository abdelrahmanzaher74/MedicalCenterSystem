package com.medical.controller;

import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/reports")
public class ReportsServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {

            // 📊 COUNTS
            Long patients = em.createQuery("SELECT COUNT(p) FROM Patient p", Long.class).getSingleResult();
            Long doctors = em.createQuery("SELECT COUNT(d) FROM Doctors d", Long.class).getSingleResult();
            Long appointments = em.createQuery("SELECT COUNT(a) FROM Appointment a", Long.class).getSingleResult();

            request.setAttribute("patients", patients);
            request.setAttribute("doctors", doctors);
            request.setAttribute("appointments", appointments);

            // 📅 Appointments per day
            List<Object[]> perDay = em.createQuery(
                "SELECT a.appointmentDate, COUNT(a) FROM Appointment a GROUP BY a.appointmentDate"
            ).getResultList();

            request.setAttribute("perDay", perDay);

            // 👨‍⚕️ Appointments per doctor
            List<Object[]> perDoctor = em.createQuery(
                "SELECT d.doctorsName, COUNT(a) FROM Appointment a JOIN a.doctorSSN d GROUP BY d.doctorsName"
            ).getResultList();

            request.setAttribute("perDoctor", perDoctor);

            // 🦠 Top diseases
            List<Object[]> diseases = em.createQuery(
                "SELECT d.diseaseName, COUNT(p) FROM Diseases d JOIN d.patientCollection p GROUP BY d.diseaseName ORDER BY COUNT(p) DESC"
            ).setMaxResults(5).getResultList();

            request.setAttribute("topDiseases", diseases);

        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();

        request.getRequestDispatcher("reports.jsp").forward(request, response);
    }
}