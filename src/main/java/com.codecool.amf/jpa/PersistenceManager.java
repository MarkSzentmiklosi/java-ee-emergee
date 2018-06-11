package com.codecool.amf.jpa;

import javax.persistence.*;

public enum PersistenceManager {
    INSTANCE;

    private EntityManagerFactory emFactory;
    private EntityManager entityManager;

    PersistenceManager() {
        emFactory = Persistence.createEntityManagerFactory("emergeePU");
        entityManager = emFactory.createEntityManager();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void close() {
        emFactory.close();
    }

    public void persistEntity(Object object) {
        try {

            entityManager.getTransaction().begin();
            entityManager.persist(object);
            entityManager.getTransaction().commit();

        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("[EXCEPTION]: Argument is not an instance of an entity class");
        } catch (TransactionRequiredException e) {
            e.printStackTrace();
            System.out.println("[EXCEPTION]: There is no active transaction when persist -> modify the database require an active transaction");
        } catch (EntityExistsException e) {
            e.printStackTrace();
            System.out.println("[EXCEPTION]: The database already contains another entity of the same type with the same primary key");
        }
    }
}