package com.codecool.amf.jpa;

import com.codecool.amf.PService;
import com.codecool.amf.auth.AuthenticationManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.User;

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
        populateDB();
    }

    private static void populateDB() {

        Address address1 = new Address("Hungary", "Budapest", "1125", "Fogaskereku utca", "5");
        Address address2 = new Address("USA", "Los Angeles", "CA 90021", "Sacramento St", "5");

        Partner ambulance = new Partner("Orszagos Mentoszolgalat", "bollaferenc@gmail.com", PService.AMBULANCE);
        Partner police = new Partner("Budapesti Rendor-fokapitanysag", "bollaferenc@gmail.com", PService.POLICE);
        Partner fire = new Partner("XIII. keruleti Tuzoltosagi Parancsnoksag", "bollaferenc@gmail.com", PService.FIRE);
        Partner carService = new Partner("Magyarorszagi Automento Szolgalat", "bollaferenc@gmail.com", PService.CAR_REPAIR);

        String password = AuthenticationManager.hashPassword("hello");

        User user1 = new User("Zamboki Panna", "pannaz@amf.com", "06301985667", "CE9352D", address1);
        User user2 = new User("Tester Odon", "todon@amf.com", "06301985998", "FA9392C", address2);

        user1.setPasswordHash(password);
        user2.setPasswordHash(password);

        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        em.persist(address1);
        em.persist(address2);

        em.persist(ambulance);
        em.persist(police);
        em.persist(fire);
        em.persist(carService);

        em.persist(user1);
        em.persist(user2);

        transaction.commit();
        System.out.println("[INFO]: Generated 2 addresses, 4 partners, 2 users");
        System.out.println(
                "[IMPORTANT]: In the persistance.xml change config attributes to: \n" +
                        "   <property name=\"hibernate.hbm2dll.auto\" value=\"create\"/>\n" +
                        "   <property name=\"javax.persistence.schema-generation.database.action\" value=\"update\"/>\n" +
                        "   <property name=\"javax.persistence.schema-generation.scripts.action\" value=\"update\"/>"
        );

        em.close();
        emf.close();
    }

    private static void generateDB() {
        EntityManager em = emf.createEntityManager();
        System.out.println("[INFO]: Gerenating DB ... Done");
    }

}
