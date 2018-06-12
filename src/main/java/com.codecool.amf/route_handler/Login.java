package com.codecool.amf.route_handler;

import com.codecool.amf.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends HttpServlet {

    TemplateEngineUtil templateEngineUtil;

    public Login(TemplateEngineUtil templateEngineUtil) {
        this.templateEngineUtil = templateEngineUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        HttpSession session = req.getSession();
        if (session.getAttribute("user") != null) {
            resp.sendRedirect("/");
        } else {
            TemplateEngine engine = templateEngineUtil.getTemplateEngine(req.getServletContext());
            WebContext context = new WebContext(req, resp, req.getServletContext());
            context.setVariable("error", false);
            engine.process("login.html", context, resp.getWriter());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/");
    }
}
