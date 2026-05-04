package com.medical.controller;

import com.medical.dao.facade.PatientFacade;
import com.medical.model.entities.Patient;
import com.medical.model.entities.Bloodtype;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/updatePatient")
public class UpdatePatientServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        try {
            PatientFacade facade = new PatientFacade();

            Patient p = facade.find(request.getParameter("ssn"));

            p.setPatientName(request.getParameter("name"));
            p.setPatientEmail(request.getParameter("email"));

            String g = request.getParameter("gender");
            if (g != null && !g.isEmpty()) {
                p.setPatientGender(g.charAt(0));
            }

            p.setPatientMaritalstatus(request.getParameter("status"));
            p.setPatientAdress(request.getParameter("address"));

            String bloodId = request.getParameter("blood_id");
            if (bloodId != null && !bloodId.isEmpty()) {
                Bloodtype b = new Bloodtype();
                b.setBloodId(Integer.parseInt(bloodId));
                p.setBloodId(b);
            }

            facade.update(p);

        } catch (Exception e) {
            e.printStackTrace();
        }

        response.sendRedirect("patients");
    }
}