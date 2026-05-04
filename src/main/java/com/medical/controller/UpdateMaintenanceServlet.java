package com.medical.controller;

import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet("/updateMaintenance")
public class UpdateMaintenanceServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Maintenance m = em.find(Maintenance.class,
                    Integer.parseInt(request.getParameter("id")));

            Date date = new SimpleDateFormat("yyyy-MM-dd")
                    .parse(request.getParameter("date"));

            m.setMaintenanceDate(date);
            m.setMaintenanceType(request.getParameter("type"));
            m.setMaintenanceStatus(request.getParameter("status"));
            m.setMaintenanceCost(new BigDecimal(request.getParameter("cost")));

            Equipment eq = em.find(Equipment.class,
                    Integer.parseInt(request.getParameter("equipment_id")));

            Maintenancecompany comp = em.find(Maintenancecompany.class,
                    Integer.parseInt(request.getParameter("company_id")));

            m.setEquipmentid(eq);
            m.setCompanyid(comp);

            em.merge(m);
            em.getTransaction().commit();

            request.getSession().setAttribute("success", "Updated Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            request.getSession().setAttribute("error", "Update Failed");
        } finally {
            em.close();
        }

        response.sendRedirect("maintenance");
    }
}