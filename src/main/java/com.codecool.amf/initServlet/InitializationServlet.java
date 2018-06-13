package com.codecool.amf.initServlet;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.emailSender;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.route_handler.*;
import com.codecool.amf.route_handler.edit_profile.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializationServlet extends HttpServlet {


    public void init() throws ServletException {
        PersistenceManager persistenceManager = createPersistanceManager();
        AuthenticationManager authenticationManager = new AuthenticationManager();

        emailSender emailSender = new emailSender();
        QueryManager queryManager = new QueryManager(persistenceManager);

        Service service = new Service(emailSender, persistenceManager, queryManager);
        Index index = new Index();
        Login login = new Login();
        CheckUserLogin checkUserLogin = new CheckUserLogin(queryManager, authenticationManager);
        Logout logout = new Logout();
        SaveName saveName = new SaveName(queryManager);
        SaveCountry saveCountry = new SaveCountry(queryManager);
        SaveCity saveCity = new SaveCity(queryManager);
        SaveZipCode saveZipCode = new SaveZipCode(queryManager);
        SaveStreet saveStreet = new SaveStreet(queryManager);
        SaveHouseNum saveHouseNum = new SaveHouseNum(queryManager);
        SavePhoneNumber savePhoneNumber = new SavePhoneNumber(queryManager);
        SaveIdCard saveIdCard = new SaveIdCard(queryManager);

        getServletContext().setAttribute("servletService", service);
        getServletContext().setAttribute("servletIndex", index);
        getServletContext().setAttribute("servletLogin", login);
        getServletContext().setAttribute("servletCheckUser", checkUserLogin);
        getServletContext().setAttribute("servletLogout", logout);
        getServletContext().setAttribute("servletSaveName", saveName);
        getServletContext().setAttribute("servletSaveCounrty", saveCountry);
        getServletContext().setAttribute("servletSaveCity", saveCity);
        getServletContext().setAttribute("servletSaveZipCode", saveZipCode);
        getServletContext().setAttribute("servletSaveStreet", saveStreet);
        getServletContext().setAttribute("servletSaveHouseNum", saveHouseNum);
        getServletContext().setAttribute("servletSavePhoneNumber", savePhoneNumber);
        getServletContext().setAttribute("servletSaveIdCard", saveIdCard);

    }

    private PersistenceManager createPersistanceManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emergeePU");
        EntityManager em = emf.createEntityManager();
        return new PersistenceManager(em, emf);
    }
}