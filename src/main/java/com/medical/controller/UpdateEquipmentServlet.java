package com.medical.controller;

import com.medical.model.entities.Equipment;
import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/updateEquipment")
public class UpdateEquipmentServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            int id = Integer.parseInt(request.getParameter("id"));
            Equipment e = em.find(Equipment.class, id);

            if (e != null) {

                e.setEquipmentName(request.getParameter("name"));
                e.setEquipmentModel(request.getParameter("model"));
                e.setMaintenanceinterval(request.getParameter("interval"));

                try {
                    e.setEquipmentPrice(new BigDecimal(request.getParameter("price")));
                } catch (Exception ex) {}

                em.merge(e);
            }

            em.getTransaction().commit();

        } catch (Exception ex) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }

        response.sendRedirect("equipment");
    }
}