package com.medical.controller;

import com.medical.dao.facade.MaintenanceFacade;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteMaintenance")
public class DeleteMaintenanceServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            MaintenanceFacade facade = new MaintenanceFacade();
            facade.delete(id);

            request.getSession().setAttribute("success", "Deleted Successfully");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("error", "Delete Failed");
        }

        response.sendRedirect("maintenance");
    }
}