package com.medical.controller;

import com.medical.dao.facade.AppointmentFacade;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteAppointment")
public class DeleteAppointmentServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            Integer id = Integer.parseInt(request.getParameter("id"));

            AppointmentFacade facade = new AppointmentFacade();
            facade.delete(id);

            System.out.println("✅ Appointment Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("appointments");
    }
}