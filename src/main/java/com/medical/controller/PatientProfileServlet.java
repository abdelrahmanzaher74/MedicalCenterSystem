package com.medical.controller;

import com.medical.model.entities.Patient;
import com.medical.model.entities.Diseases;

import jakarta.persistence.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.*;

@WebServlet("/patientProfile")
public class PatientProfileServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    // ================= GET =================
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        // diseases list
        List<Diseases> allDiseases =
                em.createNamedQuery("Diseases.findAll", Diseases.class)
                  .getResultList();

        request.setAttribute("allDiseases", allDiseases);

        String ssn = request.getParameter("ssn");

        if (ssn != null && !ssn.trim().isEmpty()) {

            Patient p = em.find(Patient.class, ssn);

            if (p != null) {

                request.setAttribute("selectedPatient", p);

                // phones
                List<String> phones = em.createQuery(
                        "SELECT ph.phone FROM Patientphone ph WHERE ph.patientssn.patientssn = :ssn",
                        String.class
                )
                .setParameter("ssn", ssn)
                .getResultList();

                request.setAttribute("phones", phones);

                // diseases
                List<Object[]> diseases = em.createQuery(
                        "SELECT d.diseaseID, d.diseaseName FROM Diseases d JOIN d.patientCollection p WHERE p.patientssn = :ssn"
                )
                .setParameter("ssn", ssn)
                .getResultList();

                request.setAttribute("diseases", diseases);

                // 🔥 FIXED APPOINTMENTS
                List<Object[]> appointments = em.createQuery(
                        "SELECT a.appointmentid, a.appointmentDate, d.doctorsName " +
                        "FROM Appointment a JOIN a.doctorSSN d " +
                        "WHERE a.patientSSN.patientssn = :ssn",
                        Object[].class
                )
                .setParameter("ssn", ssn)
                .getResultList();

                request.setAttribute("appointments", appointments);
            }
        }

        em.close();

        request.getRequestDispatcher("patientProfile.jsp").forward(request, response);
    }

    // ================= POST =================
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        String ssn = request.getParameter("ssn");

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            if ("add".equals(action)) {

                String[] diseaseIds = request.getParameterValues("diseaseIds");

                if (diseaseIds != null) {
                    for (String dId : diseaseIds) {

                        em.createNativeQuery(
                            "INSERT INTO Patient_Diseases (Patient_SSN, Disease_ID) " +
                            "SELECT ?, ? WHERE NOT EXISTS (" +
                            "SELECT 1 FROM Patient_Diseases WHERE Patient_SSN=? AND Disease_ID=?)"
                        )
                        .setParameter(1, ssn)
                        .setParameter(2, Integer.parseInt(dId))
                        .setParameter(3, ssn)
                        .setParameter(4, Integer.parseInt(dId))
                        .executeUpdate();
                    }
                }
            }

            else if ("delete".equals(action)) {

                String diseaseId = request.getParameter("diseaseId");

                em.createNativeQuery(
                    "DELETE FROM Patient_Diseases WHERE Patient_SSN=? AND Disease_ID=?"
                )
                .setParameter(1, ssn)
                .setParameter(2, Integer.parseInt(diseaseId))
                .executeUpdate();
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }

        em.close();

        response.sendRedirect("patientProfile?ssn=" + ssn);
    }
}