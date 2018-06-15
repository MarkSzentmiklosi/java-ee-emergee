package com.codecool.amf.route_handler;

import com.codecool.amf.jpa.QueryManager;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CheckRegistrationEmail extends HttpServlet {
    private QueryManager queryManager;

    public CheckRegistrationEmail(QueryManager queryManager) {
        this.queryManager = queryManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String email = req.getParameter("email");
        String response = "newEmail";

        if (queryManager.isEmailRegistered(email)) {
            response = "existingEmail";
        }

        resp.getWriter().write(response);
    }
}

