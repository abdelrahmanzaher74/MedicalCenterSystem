package com.medical.controller;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deletePatient")
public class DeletePatientServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        EntityManager em = emf.createEntityManager();

        try {
            String id = request.getParameter("id");

            em.getTransaction().begin();

            // 🧨 امسح diseases relation (جدول الربط)
            em.createNativeQuery("DELETE FROM Patient_Diseases WHERE Patient_SSN = ?")
              .setParameter(1, id)
              .executeUpdate();

            // 🧨 امسح المريض
            com.medical.model.entities.Patient p = em.find(com.medical.model.entities.Patient.class, id);

            if (p != null) {
                em.remove(p);
                System.out.println("✅ Patient Deleted");
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        em.close();

        response.sendRedirect("patients");
    }
}