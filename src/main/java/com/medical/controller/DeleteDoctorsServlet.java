package com.medical.controller;

import com.medical.dao.facade.DoctorsFacade;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteDoctor")
public class DeleteDoctorsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            String ssn = request.getParameter("id");

            DoctorsFacade facade = new DoctorsFacade();
            facade.delete(ssn);

            System.out.println("✅ Doctor Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("doctors");
    }
}