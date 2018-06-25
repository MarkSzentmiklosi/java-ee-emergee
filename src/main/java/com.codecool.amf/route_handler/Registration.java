//package com.codecool.amf.route_handler;
//
//import com.codecool.amf.config.TemplateEngineUtil;
//import org.thymeleaf.TemplateEngine;
//import org.thymeleaf.context.WebContext;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//public class Registration extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        String email = req.getParameter("registration_email");
//        String password = req.getParameter("registration_password");
//
//        session.setAttribute("password", password);
//        session.setAttribute("email", email);
//        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
//        WebContext context = new WebContext(req, resp, req.getServletContext());
//        engine.process("update-profile.html", context, resp.getWriter());
//    }
//}
