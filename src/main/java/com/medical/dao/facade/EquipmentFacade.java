package com.medical.dao.facade;

import com.medical.model.entities.Equipment;
import jakarta.persistence.*;
import java.util.List;

public class EquipmentFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    public void create(Equipment e) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
        em.close();
    }

    public void update(Equipment e) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
        em.close();
    }

    public void delete(Integer id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Equipment e = em.find(Equipment.class, id);
        if (e != null) em.remove(e);

        em.getTransaction().commit();
        em.close();
    }

    public List<Equipment> findAll() {
        EntityManager em = emf.createEntityManager();
        List<Equipment> list =
                em.createNamedQuery("Equipment.findAll", Equipment.class)
                  .getResultList();
        em.close();
        return list;
    }
}