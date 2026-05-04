package com.medical.controller;

import com.medical.dao.facade.EquipmentFacade;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteEquipment")
public class DeleteEquipmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            new EquipmentFacade().delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("equipment");
    }
}