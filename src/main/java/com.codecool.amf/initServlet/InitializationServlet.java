package com.codecool.amf.initServlet;

import com.codecool.amf.config.TemplateEngineUtil;
import com.codecool.amf.config.ThymeleafConfig;
import com.codecool.amf.emailSender;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.route_handler.Index;
import com.codecool.amf.route_handler.Login;
import com.codecool.amf.route_handler.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializationServlet extends HttpServlet {


    public void init() throws ServletException {
        PersistenceManager persistenceManager = createPersistanceManager();
        TemplateEngineUtil templateEngineUtil = new TemplateEngineUtil();
        ThymeleafConfig thymeleafConfig = new ThymeleafConfig(templateEngineUtil);

        emailSender emailSender = new emailSender();
        QueryManager queryManager = new QueryManager(persistenceManager);

        Service service = new Service(emailSender, persistenceManager, queryManager);
        Index index = new Index(templateEngineUtil);
        Login login = new Login(templateEngineUtil);

        getServletContext().setAttribute("servletService", service);
        getServletContext().setAttribute("servletIndex", index);
        getServletContext().setAttribute("servletLogin", login);

    }

    private PersistenceManager createPersistanceManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emergeePU");
        EntityManager em = emf.createEntityManager();
        return new PersistenceManager(em, emf);
    }
}