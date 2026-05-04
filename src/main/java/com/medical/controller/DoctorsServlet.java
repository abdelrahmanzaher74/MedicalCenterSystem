package com.medical.controller;

import com.medical.dao.facade.DoctorsFacade;
import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors")
public class DoctorsServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        DoctorsFacade facade = new DoctorsFacade();

        request.setAttribute("doctorsList", facade.findAll());

        request.setAttribute("departments",
                em.createQuery("SELECT d FROM Departments d", Departments.class).getResultList());

        em.close();

        request.getRequestDispatcher("doctors.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            DoctorsFacade facade = new DoctorsFacade();

            Doctors d = new Doctors();

            String ssn = request.getParameter("ssn");

            if (em.find(Doctors.class, ssn) != null) {
                request.getSession().setAttribute("error", "Doctor already exists");
                response.sendRedirect("doctors");
                return;
            }

            d.setDoctorSSN(ssn);

            // ✅ الاسم الصح
            d.setDoctorsName(request.getParameter("name"));

            // ✅ الإيميل الصح
            d.setDoctorsEmail(request.getParameter("email"));

            // ✅ التخصص
            d.setDoctorsSpecialization(request.getParameter("specialization"));

            // ✅ الجندر
            String gender = request.getParameter("gender");
            if (gender != null && !gender.isEmpty()) {
                d.setDoctorsGender(gender.charAt(0));
            }

            // ✅ القسم
            int depId = Integer.parseInt(request.getParameter("department"));
            d.setDepartmentid(em.find(Departments.class, depId));

            // ⚠️ مهم جدًا (الـ Entity عندك فيه Price required)
            Prices price = em.find(Prices.class, 1); // مؤقتًا ID=1
            d.setPriceid(price);

            facade.create(d);

            request.getSession().setAttribute("success", "Doctor Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error occurred");
        } finally {
            em.close();
        }

        response.sendRedirect("doctors");
    }
}