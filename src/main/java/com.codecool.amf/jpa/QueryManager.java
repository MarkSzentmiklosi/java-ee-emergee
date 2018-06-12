package com.codecool.amf.jpa;

import com.codecool.amf.model.PService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class QueryManager {


    private PersistenceManager persistenceManager;

    public QueryManager(PersistenceManager persistenceManager) {

        this.persistenceManager = persistenceManager;
    }

    public List selectUserByEmail(String email) {
        EntityManager entityManager = persistenceManager.getEntityManager();

        String queryString = "SELECT U FROM User U WHERE U.email = :email";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);

        List result = query.getResultList();

        return result;
    }

    public List selectPartnerByService(PService service) {
        EntityManager entityManager = persistenceManager.getEntityManager();

        String queryString = "SELECT P FROM Partner P WHERE P.service = :service";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("service", service);

        List result = query.getResultList();

        return result;
    }
}