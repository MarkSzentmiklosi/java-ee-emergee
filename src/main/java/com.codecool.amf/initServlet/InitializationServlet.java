package com.codecool.amf.initServlet;

import com.codecool.amf.emailSender;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.route_handler.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializationServlet extends HttpServlet {
    public void init() throws ServletException {
        PersistenceManager persistenceManager = createPersistanceManager();

        emailSender emailSender = new emailSender();
        QueryManager queryManager = new QueryManager(persistenceManager);

        Service service = new Service(emailSender, persistenceManager, queryManager);
        getServletContext().setAttribute("servletService", service);
    }

    private PersistenceManager createPersistanceManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emergeePU");
        EntityManager em = emf.createEntityManager();
        return new PersistenceManager(em, emf);
    }
}