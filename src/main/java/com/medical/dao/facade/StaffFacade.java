package com.medical.dao.facade;

import com.medical.model.entities.Staff;
import jakarta.persistence.*;
import java.util.List;

public class StaffFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    // ➕ CREATE
    public void create(Staff s) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
        em.close();
    }

    // ✏️ UPDATE
    public void update(Staff s) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
        em.close();
    }

    // ❌ DELETE (FIXED STRING SSN 🔥)
    public void delete(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Staff s = em.find(Staff.class, id);

        if (s != null) {
            em.remove(s);
        }

        em.getTransaction().commit();
        em.close();
    }

    // 🔍 FIND
    public Staff find(String id) {
        EntityManager em = emf.createEntityManager();
        Staff s = em.find(Staff.class, id);
        em.close();
        return s;
    }

    // 📋 GET ALL
    public List<Staff> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Staff> list =
                em.createNamedQuery("Staff.findAll", Staff.class)
                  .getResultList();
        em.close();
        return list;
    }
}