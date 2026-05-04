package com.medical.dao.facade;

import com.medical.model.entities.Appointment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class AppointmentFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    // ➕ ADD
    public void create(Appointment a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    // ✏️ UPDATE
    public void update(Appointment a) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(a);
        em.getTransaction().commit();
        em.close();
    }

    // ❌ DELETE (FIXED 🔥 يشتغل مع القديم والجديد)
    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Appointment a = em.find(Appointment.class, id);

        if (a != null) {
            em.remove(a);
        }

        em.getTransaction().commit();
        em.close();
    }

    // 🔍 FIND
    public Appointment find(Integer id) {
        EntityManager em = emf.createEntityManager();
        Appointment a = em.find(Appointment.class, id);
        em.close();
        return a;
    }

    // 📋 GET ALL
    public List<Appointment> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Appointment> list =
                em.createNamedQuery("Appointment.findAll", Appointment.class)
                  .getResultList();
        em.close();
        return list;
    }
}