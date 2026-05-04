package com.medical.controller;

import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateAppointment")
public class UpdateAppointmentServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            Integer id = Integer.parseInt(request.getParameter("id"));

            Appointment a = em.find(Appointment.class, id);

            if (a == null) {
                response.sendRedirect("appointments");
                return;
            }

            // 🔥 VALIDATION DATE
            String dateStr = request.getParameter("date");

            if (dateStr == null || dateStr.isEmpty()) {
                System.out.println("❌ Date is missing");
                em.getTransaction().rollback();
                response.sendRedirect("appointments");
                return;
            }

            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                    .parse(dateStr);

            a.setAppointmentDate(date);

            // Patient
            String ssn = request.getParameter("patient");
            Patient p = em.find(Patient.class, ssn);
            a.setPatientSSN(p);

            // Doctor
            String doctorId = request.getParameter("doctor");
            a.setDoctorSSN(em.find(Doctors.class, doctorId));

            // Service (optional)
            String serviceId = request.getParameter("service");
            if (serviceId != null && !serviceId.isEmpty()) {
                a.setServiceid(em.find(Service.class, Integer.parseInt(serviceId)));
            } else {
                a.setServiceid(null);
            }

            // Payment
            String methodId = request.getParameter("method");
            a.setMethodid(em.find(PaymentMethod.class, Integer.parseInt(methodId)));

            em.merge(a);

            em.getTransaction().commit();

            System.out.println("✅ UPDATED SUCCESS");

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        response.sendRedirect("appointments");
    }
}