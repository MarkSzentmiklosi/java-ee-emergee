package com.codecool.amf.jpa;

import com.codecool.amf.model.PService;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class QueryManager {

    public static List selectUserByEmail(String email) {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();

        String queryString = "SELECT U FROM User U WHERE U.email = :email";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);

        List result = query.getResultList();

        entityManager.close();

        return result;
    }

    public static List selectPartnerByService(PService service) {
        EntityManager entityManager = PersistenceManager.INSTANCE.getEntityManager();

        String queryString = "SELECT P FROM Partner P WHERE P.service = :service";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("service", service);

        List result = query.getResultList();

        entityManager.close();

        return result;
    }
}