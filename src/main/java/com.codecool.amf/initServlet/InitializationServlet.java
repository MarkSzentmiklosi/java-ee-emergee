package com.codecool.amf.initServlet;

import com.codecool.amf.Controller.ProfileController;
import com.codecool.amf.EmailSender;
import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.route_handler.CheckUserLogin;
import com.codecool.amf.route_handler.GoogleLogin;
import com.codecool.amf.route_handler.Index;
import com.codecool.amf.route_handler.Login;
import com.codecool.amf.route_handler.Logout;
import com.codecool.amf.route_handler.Registration;
import com.codecool.amf.route_handler.Service;
import com.codecool.amf.route_handler.UpdateProfile;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InitializationServlet extends HttpServlet {


    public void init() throws ServletException {
        PersistenceManager persistenceManager = createPersistanceManager();
        AuthenticationManager authenticationManager = new AuthenticationManager();

        EmailSender emailSender = new EmailSender();
        QueryManager queryManager = new QueryManager(persistenceManager);
        ProfileController profileController = new ProfileController(queryManager);

        Service service = new Service(emailSender, persistenceManager, queryManager);
        Index index = new Index();
        Login login = new Login();
        Registration registration = new Registration(authenticationManager, persistenceManager);
        GoogleLogin googleLogin = new GoogleLogin(queryManager);
        CheckUserLogin checkUserLogin = new CheckUserLogin(queryManager, authenticationManager);
        Logout logout = new Logout();
        SaveProfileSettings saveProfile = new SaveProfileSettings(profileController);
        UpdateProfile updateProfile = new UpdateProfile(persistenceManager, queryManager);

        getServletContext().setAttribute("servletService", service);
        getServletContext().setAttribute("servletIndex", index);
        getServletContext().setAttribute("servletLogin", login);
        getServletContext().setAttribute("servletGoogleLogin", googleLogin);
        getServletContext().setAttribute("servletCheckUser", checkUserLogin);
        getServletContext().setAttribute("servletLogout", logout);
        getServletContext().setAttribute("servletUpdateProfile", updateProfile);
        getServletContext().setAttribute("servletRegistration", registration);

        getServletContext().setAttribute("servletSaveProfile", saveProfile);
    }

    private PersistenceManager createPersistanceManager() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("emergeePU");
        EntityManager em = emf.createEntityManager();
        return new PersistenceManager(em, emf);
    }
}