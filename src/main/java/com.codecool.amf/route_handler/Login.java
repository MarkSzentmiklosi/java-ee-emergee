package com.codecool.amf.route_handler;

import com.codecool.amf.auth.AuthenticationManager;
import com.codecool.amf.config.TemplateEngineUtil;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.User;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

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

        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/");
        } else {

            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("error", false);
            engine.process("login.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        List<User> users = QueryManager.selectUserByEmail(username);
        User loginUser;

        try {
            loginUser = users.get(0);
            if (AuthenticationManager.checkPassword(password, loginUser.getPasswordHash())) {
                User user = new User(loginUser.getName(), loginUser.getEmail(), loginUser.getPhoneNumber(), loginUser.getIdCardNum(), loginUser.getAddress());
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                resp.sendRedirect("/");
            } else {
                throw new Exception("Authentication error");
            }
        } catch (Exception e) {
            TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("error", true);
            engine.process("login.html", context, resp.getWriter());
        }

    }
}
