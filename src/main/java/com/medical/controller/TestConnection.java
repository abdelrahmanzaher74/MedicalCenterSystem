package com.medical.controller;

import com.medical.dao.facade.PatientFacade;
import com.medical.model.entities.Patient;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/test")
public class TestConnection extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {

            PatientFacade facade = new PatientFacade();

            List<Patient> list = facade.findAll();

            out.println("<h1>🔥 SUCCESS</h1>");
            out.println("<h2>Patients count: " + list.size() + "</h2>");

        } catch (Exception e) {

            out.println("<h1>❌ ERROR</h1>");
            e.printStackTrace(out);
        }
    }
}