package com.medical.controller;

import com.medical.dao.facade.AppointmentFacade;
import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/appointments")
public class AppointmentServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        AppointmentFacade facade = new AppointmentFacade();

        List<Appointment> list = facade.findAll();

        request.setAttribute("appointments", list);

        // 🔥 DASHBOARD
        request.setAttribute("totalAppointments", list.size());

        request.setAttribute("withService",
                list.stream().filter(a -> a.getServiceid() != null).count());

        request.setAttribute("withoutService",
                list.stream().filter(a -> a.getServiceid() == null).count());

        // dropdowns (زي ما هو)
        request.setAttribute("doctors",
                em.createQuery("SELECT d FROM Doctors d", Doctors.class).getResultList());

        request.setAttribute("services",
                em.createQuery("SELECT s FROM Service s", Service.class).getResultList());

        request.setAttribute("methods",
                em.createQuery("SELECT m FROM PaymentMethod m", PaymentMethod.class).getResultList());

        em.close();

        request.getRequestDispatcher("appointment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            AppointmentFacade facade = new AppointmentFacade();

            int id = Integer.parseInt(request.getParameter("id"));

            if (em.find(Appointment.class, id) != null) {
                request.setAttribute("error", "Appointment ID already exists");
                doGet(request, response);
                return;
            }

            Appointment a = new Appointment();
            a.setAppointmentid(id);

            Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm")
                    .parse(request.getParameter("date"));
            a.setAppointmentDate(date);

            // Patient
            String patientSSN = request.getParameter("patient");
            Patient patient = em.find(Patient.class, patientSSN);

            if (patient == null) {
                request.setAttribute("error", "Patient not found");
                doGet(request, response);
                return;
            }

            a.setPatientSSN(patient);

            // Doctor
            a.setDoctorSSN(em.find(Doctors.class, request.getParameter("doctor")));

            // Service (optional)
            String serviceId = request.getParameter("service");
            if (serviceId != null && !serviceId.isEmpty()) {
                a.setServiceid(em.find(Service.class, Integer.parseInt(serviceId)));
            } else {
                a.setServiceid(null);
            }

            // Payment
            a.setMethodid(em.find(PaymentMethod.class,
                    Integer.parseInt(request.getParameter("method"))));

            facade.create(a);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error: " + e.getMessage());
            doGet(request, response);
            return;
        } finally {
            em.close();
        }

        response.sendRedirect("appointments");
    }
}