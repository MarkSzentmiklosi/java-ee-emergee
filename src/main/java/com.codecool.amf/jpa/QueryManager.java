package com.codecool.amf.jpa;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class QueryManager {
    private static final EntityManager entityManager = JpaManager.getInstance();

    public static List selectUserByEmail(String email) {
        String queryString = "SELECT U FROM User U WHERE U.email = :email";
        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);
        return query.getResultList();
    }

}
