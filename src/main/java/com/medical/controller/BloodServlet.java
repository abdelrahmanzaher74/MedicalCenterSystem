package com.medical.controller;

import com.medical.model.entities.Bloodtype;
import jakarta.persistence.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/blood")
public class BloodServlet extends HttpServlet {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 🚫 منع الكاش
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        EntityManager em = emf.createEntityManager();

        List<Bloodtype> list =
                em.createQuery("SELECT b FROM Bloodtype b ORDER BY b.bloodId ASC", Bloodtype.class)
                  .getResultList();

        request.setAttribute("bloodList", list);

        em.close();

        request.getRequestDispatcher("blood.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        EntityManager em = emf.createEntityManager();
        String action = request.getParameter("action");

        em.getTransaction().begin();

        try {

            // ADD
            if ("add".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));

                Bloodtype existing = em.find(Bloodtype.class, id);

                if (existing == null) {

                    Bloodtype b = new Bloodtype();
                    b.setBloodId(id);
                    b.setBloodname(request.getParameter("name"));

                    em.persist(b);

                    System.out.println("✅ Added Blood: " + b.getBloodname());

                } else {
                    System.out.println("⚠️ Already exists ID: " + id);
                }
            }

            // UPDATE
            if ("update".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Bloodtype b = em.find(Bloodtype.class, id);

                if (b != null) {
                    b.setBloodname(request.getParameter("name"));
                    em.merge(b);
                }
            }

            // DELETE
            if ("delete".equals(action)) {

                int id = Integer.parseInt(request.getParameter("id"));
                Bloodtype b = em.find(Bloodtype.class, id);

                if (b != null) em.remove(b);
            }

            em.getTransaction().commit();

        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        }

        em.close();

        // 🔥 Redirect يضمن إعادة تحميل الصفحة
        response.sendRedirect(request.getContextPath() + "/blood");
    }
}