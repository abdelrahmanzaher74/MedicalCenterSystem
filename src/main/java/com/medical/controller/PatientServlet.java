package com.medical.controller;

import com.medical.dao.facade.PatientFacade;
import com.medical.model.entities.Patient;
import com.medical.model.entities.Bloodtype;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

// 🔥 ADD
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;
import com.medical.model.entities.Patientphone;

@WebServlet("/patients")
public class PatientServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PatientFacade facade = new PatientFacade();

        List<Patient> list = facade.findAll();

        request.setAttribute("patients", list);

        // 🔥 FIX LAZY
        EntityManager em = emf.createEntityManager();
        Map<String, String> phoneMap = new HashMap<>();

        for (Patient p : list) {
            try {
                String phone = em.createQuery(
                        "SELECT ph.phone FROM Patientphone ph WHERE ph.patientssn.patientssn = :ssn",
                        String.class
                )
                .setParameter("ssn", p.getPatientssn())
                .setMaxResults(1)
                .getSingleResult();

                phoneMap.put(p.getPatientssn(), phone);

            } catch (Exception e) {
                phoneMap.put(p.getPatientssn(), "");
            }
        }

        // ✅ ✨ إضافة Blood List هنا (المكان الصح)
        List<Bloodtype> bloodList =
                em.createQuery("SELECT b FROM Bloodtype b", Bloodtype.class)
                  .getResultList();

        request.setAttribute("bloodList", bloodList);

        em.close();

        request.setAttribute("phoneMap", phoneMap);

        request.getRequestDispatcher("patient.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            PatientFacade facade = new PatientFacade();

            String ssn = request.getParameter("ssn");
            String email = request.getParameter("email");

            if (facade.find(ssn) != null) {
                request.setAttribute("error", "SSN already exists");
                doGet(request, response);
                return;
            }

            if (email != null && !email.isEmpty() && facade.findByEmail(email) != null) {
                request.setAttribute("error", "Email already exists");
                doGet(request, response);
                return;
            }

            Patient p = new Patient();

            p.setPatientssn(ssn);
            p.setPatientName(request.getParameter("name"));
            p.setPatientEmail(email);

            String g = request.getParameter("gender");
            if (g != null && !g.isEmpty()) {
                p.setPatientGender(g.charAt(0));
            }

            p.setPatientMaritalstatus(request.getParameter("status"));
            p.setPatientAdress(request.getParameter("address"));

            // 🔥 ADD BIRTH DATE
            String birth = request.getParameter("birth_date");
            if (birth != null && !birth.isEmpty()) {
                p.setPatientBirthDate(new SimpleDateFormat("yyyy-MM-dd").parse(birth));
            }

            // BLOOD
            String bloodId = request.getParameter("blood_id");

            if (bloodId != null && !bloodId.isEmpty()) {

                EntityManager em = emf.createEntityManager();

                Bloodtype b = em.find(Bloodtype.class, Integer.parseInt(bloodId));

                if (b == null) {
                    request.setAttribute("error", "Invalid Blood Type");
                    doGet(request, response);
                    return;
                }

                p.setBloodId(b);
                em.close();
            }

            facade.create(p);

            // 🔥 ADD PHONE SAVE
            String phone = request.getParameter("phone");

            if (phone != null && !phone.isEmpty()) {

                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();

                Patientphone pp = new Patientphone();
                pp.setPhone(phone);
                pp.setPatientssn(p);

                em.persist(pp);

                em.getTransaction().commit();
                em.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", e.toString());
            doGet(request, response);
            return;
        }

        response.sendRedirect("patients");
    }
}