package com.medical.controller;

import com.medical.dao.facade.StaffFacade;
import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/staff")
public class StaffServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        StaffFacade facade = new StaffFacade();

        List<Staff> list = facade.findAll();

        request.setAttribute("staffList", list);

        // departments dropdown
        request.setAttribute("departments",
                em.createQuery("SELECT d FROM Departments d", Departments.class).getResultList());

        em.close();

        request.getRequestDispatcher("staff.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            StaffFacade facade = new StaffFacade();

            Staff s = new Staff();

            // SSN (STRING)
            String ssn = request.getParameter("ssn");

            if (em.find(Staff.class, ssn) != null) {
                request.getSession().setAttribute("error", "Staff already exists");
                response.sendRedirect("staff");
                return;
            }

            s.setStaffSSN(ssn);

            // Name
            s.setStName(request.getParameter("name"));

            // Email
            s.setStEmail(request.getParameter("email"));

            // Gender
            String gender = request.getParameter("gender");
            if (gender != null && !gender.isEmpty()) {
                s.setStGender(gender.charAt(0));
            }

            // Salary
            try {
                s.setStSalary(new BigDecimal(request.getParameter("salary")));
            } catch (Exception e) {}

            // Date
            try {
                Date date = new SimpleDateFormat("yyyy-MM-dd")
                        .parse(request.getParameter("start_date"));
                s.setStStartDate(date);
            } catch (Exception e) {}

            // Department
            int depId = Integer.parseInt(request.getParameter("department"));
            s.setDepartmentid(em.find(Departments.class, depId));

            facade.create(s);

            request.getSession().setAttribute("success", "Staff Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error occurred");
        } finally {
            em.close();
        }

        response.sendRedirect("staff");
    }
}