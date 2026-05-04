package com.medical.controller;

import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateStaff")
public class UpdateStaffServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            String ssn = request.getParameter("ssn");

            Staff s = em.find(Staff.class, ssn);

            if (s != null) {

                s.setStName(request.getParameter("name"));
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

                em.merge(s);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        response.sendRedirect("staff");
    }
}