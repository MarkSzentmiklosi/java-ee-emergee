package com.codecool.amf.route_handler;

import com.codecool.amf.config.TemplateEngineUtil;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class GoogleLogin extends HttpServlet {

    private final QueryManager queryManager;


    public GoogleLogin(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType("text/html");

        try {
            String idToken = req.getParameter("id_token");
            GoogleIdToken.Payload payLoad = IdTokenVerifierAndParser.getPayload(idToken);


            if (existingUser(req, resp, payLoad)) {
                resp.sendRedirect("/");
            } else {
                signUpGoogleUser(req, resp, payLoad);
            }

        } catch (Exception e) {
            System.out.println(e.toString());
            throw new RuntimeException(e);
        }

    }

    private void signUpGoogleUser(HttpServletRequest req, HttpServletResponse resp, GoogleIdToken.Payload payLoad) throws IOException {
        String name = (String) payLoad.get("name");
        String email = payLoad.getEmail();
        HttpSession session = req.getSession();
        session.setAttribute("userName", name);
        session.setAttribute("email", email);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        engine.process("update-profile.html", context, resp.getWriter());
    }

    private boolean existingUser(HttpServletRequest req, HttpServletResponse resp, GoogleIdToken.Payload payLoad) throws IOException {
        try {

            String email = payLoad.getEmail();
            List<User> users = queryManager.selectUserByEmail(email);
            User loginUser = users.get(0);
            User user = new User(loginUser.getName(), loginUser.getEmail(), loginUser.getPhoneNumber(), loginUser.getIdCardNum(), loginUser.getAddress());
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}

