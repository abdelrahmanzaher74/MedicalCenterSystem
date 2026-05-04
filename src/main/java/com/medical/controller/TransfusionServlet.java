package com.medical.controller;

import com.medical.model.entities.Transfusion;

import jakarta.persistence.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/transfusion")
public class TransfusionServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();

        List<Transfusion> list =
                em.createNamedQuery("Transfusion.findAll", Transfusion.class)
                  .getResultList();

        request.setAttribute("transfusionList", list);

        em.close();

        request.getRequestDispatcher("transfusion.jsp").forward(request, response);
    }
}