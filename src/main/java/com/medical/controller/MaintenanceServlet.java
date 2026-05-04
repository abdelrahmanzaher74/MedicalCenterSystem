package com.medical.controller;

import com.medical.dao.facade.MaintenanceFacade;
import com.medical.model.entities.*;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/maintenance")
public class MaintenanceServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        MaintenanceFacade facade = new MaintenanceFacade();

        List<Maintenance> list = facade.findAll();

        request.setAttribute("maintenance", list);

        // 🔥 DASHBOARD
        request.setAttribute("total", list.size());
        request.setAttribute("pending",
                list.stream().filter(m -> "Pending".equals(m.getMaintenanceStatus())).count());
        request.setAttribute("completed",
                list.stream().filter(m -> "Completed".equals(m.getMaintenanceStatus())).count());

        // dropdowns
        request.setAttribute("equipments",
                em.createQuery("SELECT e FROM Equipment e", Equipment.class).getResultList());

        request.setAttribute("companies",
                em.createQuery("SELECT c FROM Maintenancecompany c", Maintenancecompany.class).getResultList());

        em.close();

        request.getRequestDispatcher("maintenance.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        try {
            MaintenanceFacade facade = new MaintenanceFacade();

            Maintenance m = new Maintenance();

            m.setMaintenanceID(Integer.parseInt(request.getParameter("id")));

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

            facade.create(m);

            request.getSession().setAttribute("success", "Maintenance Added Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Error adding maintenance");
        } finally {
            em.close();
        }

        response.sendRedirect("maintenance");
    }
}