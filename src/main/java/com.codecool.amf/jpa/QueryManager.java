package com.codecool.amf.jpa;

import com.codecool.amf.model.Address;
import com.codecool.amf.model.PService;
import com.codecool.amf.model.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

public class QueryManager {


    private EntityManager entityManager;

    public QueryManager(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public List selectUserByEmail(String email) {

        String queryString = "SELECT U FROM User U WHERE U.email = :email";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("email", email);

        List result = query.getResultList();

        return result;
    }

    public List selectPartnerByService(PService service) {

        String queryString = "SELECT P FROM Partner P WHERE P.service = :service";

        Query query = entityManager.createQuery(queryString);
        query.setParameter("service", service);

        List result = query.getResultList();

        return result;
    }

    public void updateUserName(User user, String inputName) {

        entityManager.getTransaction().begin();
        user.setName(inputName);
        entityManager.getTransaction().commit();
    }

    public void updateCountry(User user, String input) {
        Address newAddress = user.getAddress();

        entityManager.getTransaction().begin();
        newAddress.setCountry(input);
        entityManager.getTransaction().commit();
    }

    public void updateHouseNum(User user, String input) {
        Address newAddress = user.getAddress();

        entityManager.getTransaction().begin();
        newAddress.setHouseNum(input);
        entityManager.getTransaction().commit();

    }

    public void updateIdCardNum(User user, String input) {

        entityManager.getTransaction().begin();
        user.setIdCardNum(input);
        entityManager.getTransaction().commit();
    }

    public void updatePhoneNumber(User user, String input) {

        entityManager.getTransaction().begin();
        user.setPhoneNumber(input);
        entityManager.getTransaction().commit();
    }

    public void updateStreet(User user, String input) {
        Address newAddress = user.getAddress();

        entityManager.getTransaction().begin();
        newAddress.setStreet(input);
        entityManager.getTransaction().commit();
    }

    public void updateZipCode(User user, String input) {
        Address newAddress = user.getAddress();

        entityManager.getTransaction().begin();
        newAddress.setZipCode(input);
        entityManager.getTransaction().commit();
    }

    public void updateCity(User user, String input) {
        Address newAddress = user.getAddress();

        entityManager.getTransaction().begin();
        newAddress.setCity(input);
        entityManager.getTransaction().commit();
    }
}