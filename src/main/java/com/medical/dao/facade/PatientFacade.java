package com.medical.dao.facade;

import com.medical.model.entities.Patient;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;

public class PatientFacade {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("MedicalPU");

    // ➕ ADD
    public void create(Patient p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(p);
        em.getTransaction().commit();
        em.close();
    }

    // ✏️ UPDATE
    public void update(Patient p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.merge(p);
        em.getTransaction().commit();
        em.close();
    }

    // ❌ DELETE
    public void delete(String ssn) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Patient p = em.find(Patient.class, ssn);
        if (p != null) {
            em.remove(p);
        }

        em.getTransaction().commit();
        em.close();
    }

    // 🔍 FIND BY ID
    public Patient find(String ssn) {
        EntityManager em = emf.createEntityManager();
        Patient p = em.find(Patient.class, ssn);
        em.close();
        return p;
    }

    // 🔍 FIND BY EMAIL (🔥 الجديد)
    public Patient findByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                "SELECT p FROM Patient p WHERE p.patientEmail = :email",
                Patient.class
            )
            .setParameter("email", email)
            .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }

    // 📋 GET ALL
    public List<Patient> findAll() {
        EntityManager em = emf.createEntityManager();

        List<Patient> list =
                em.createNamedQuery("Patient.findAll", Patient.class)
                  .getResultList();

        em.close();
        return list;
    }
}