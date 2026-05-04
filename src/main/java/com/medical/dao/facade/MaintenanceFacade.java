package com.medical.dao.facade;

import com.medical.model.entities.Maintenance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class MaintenanceFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    // ➕ ADD
    public void create(Maintenance m) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(m);
        em.getTransaction().commit();
        em.close();
    }

    // ✏️ UPDATE
    public void update(Maintenance m) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(m);
        em.getTransaction().commit();
        em.close();
    }

    // ❌ DELETE
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Maintenance m = em.find(Maintenance.class, id);

        if (m != null) {
            em.remove(m);
        }

        em.getTransaction().commit();
        em.close();
    }

    // 🔍 FIND
    public Maintenance find(Integer id) {
        EntityManager em = emf.createEntityManager();
        Maintenance m = em.find(Maintenance.class, id);
        em.close();
        return m;
    }

    // 📋 GET ALL
    public List<Maintenance> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Maintenance> list =
                em.createNamedQuery("Maintenance.findAll", Maintenance.class)
                  .getResultList();
        em.close();
        return list;
    }
}