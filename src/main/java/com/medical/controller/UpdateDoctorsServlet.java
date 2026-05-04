package com.medical.controller;

import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updateDoctor")
public class UpdateDoctorsServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            String ssn = request.getParameter("ssn");

            Doctors d = em.find(Doctors.class, ssn);

            if (d != null) {

                d.setDoctorsName(request.getParameter("name"));
                d.setDoctorsEmail(request.getParameter("email"));
                d.setDoctorsSpecialization(request.getParameter("specialization"));

                String gender = request.getParameter("gender");
                if (gender != null && !gender.isEmpty()) {
                    d.setDoctorsGender(gender.charAt(0));
                }

                int depId = Integer.parseInt(request.getParameter("department"));
                d.setDepartmentid(em.find(Departments.class, depId));

                em.merge(d);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        response.sendRedirect("doctors");
    }
}