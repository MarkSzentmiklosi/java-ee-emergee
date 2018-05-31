package com.codecool.amf.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
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

}
