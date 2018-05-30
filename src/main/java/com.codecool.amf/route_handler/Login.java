package com.codecool.amf.route_handler;

import com.codecool.amf.auth.AuthenticationManager;
import com.codecool.amf.config.TemplateEngineUtil;
import com.codecool.amf.jpa.JpaManager;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login"})
public class Login extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        EntityManager entityManager = JpaManager.getInstance();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("login.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String hashedPassword = AuthenticationManager.hashPassword(password);

        // TODO: Get hashed password from db with the corresponding email address and compare it with the ones given by user
        // TODO: Handling exception (redirect to error page)
        String queryString = "SELECT U FROM User U";
        EntityManager entityManager = JpaManager.getInstance();
        javax.persistence.Query query = entityManager.createQuery(queryString);
        List results = query.getResultList();

            HttpSession session = req.getSession();
            session.setAttribute("userId", 1);
            //TODO: Get the id of the logged in user and store it in session
            resp.sendRedirect("/");
    }
}
