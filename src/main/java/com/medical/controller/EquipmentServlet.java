package com.medical.controller;

import com.medical.dao.facade.EquipmentFacade;
import com.medical.model.entities.Equipment;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/equipment")
public class EquipmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EquipmentFacade facade = new EquipmentFacade();

        request.setAttribute("equipmentList", facade.findAll());

        request.getRequestDispatcher("equipment.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            EquipmentFacade facade = new EquipmentFacade();

            Equipment e = new Equipment();

            e.setEquipmentid(Integer.parseInt(request.getParameter("id")));
            e.setEquipmentName(request.getParameter("name"));
            e.setEquipmentModel(request.getParameter("model"));
            e.setMaintenanceinterval(request.getParameter("interval"));

            try {
                e.setEquipmentPrice(new BigDecimal(request.getParameter("price")));
            } catch (Exception ex) {}

            facade.create(e);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        response.sendRedirect("equipment");
    }
}