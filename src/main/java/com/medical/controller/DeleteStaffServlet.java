package com.medical.controller;

import com.medical.dao.facade.StaffFacade;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/deleteStaff")
public class DeleteStaffServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            // SSN = STRING
            String ssn = request.getParameter("id");

            StaffFacade facade = new StaffFacade();
            facade.delete(ssn);

            System.out.println("✅ Staff Deleted");

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("staff");
    }
}