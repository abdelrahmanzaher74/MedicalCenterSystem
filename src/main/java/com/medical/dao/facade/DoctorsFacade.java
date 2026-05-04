package com.medical.dao.facade;

import com.medical.model.entities.Doctors;
import jakarta.persistence.*;
import java.util.List;

public class DoctorsFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    public void create(Doctors d) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(d);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Doctors d) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(d);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Doctors d = em.find(Doctors.class, id);

        if (d != null) {
            em.remove(d);
        }

        em.getTransaction().commit();
        em.close();
    }

    public List<Doctors> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Doctors> list =
                em.createNamedQuery("Doctors.findAll", Doctors.class)
                  .getResultList();
        em.close();
        return list;
    }
}