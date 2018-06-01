package com.codecool.amf.jpa;

import com.codecool.amf.model.HRequest;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaManager {

    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("emergeePU");
    private static EntityManager entityManager;

    private JpaManager() {
    }

    public static EntityManager getInstance() {
        if (entityManager == null) {
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }

    public static void main(String[] args) {
        generateDB();
    }

    private static void generateDB() {
        EntityManager em = emf.createEntityManager();
        System.out.println("[INFO]: Gerenating DB ... Done");
        em.close();
        emf.close();
    }

    public static void persist(HRequest hRequest) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        try {

            em.persist(hRequest);
            transaction.commit();
            em.clear();
            em.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("[WARNING]: Cannot persist help request");
        }
    }
}
