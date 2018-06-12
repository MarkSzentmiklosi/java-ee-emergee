package com.codecool.amf.route_handler;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class CheckUserLogin extends HttpServlet {
    private QueryManager queryManager;
    private AuthenticationManager authenticationManager;

    public CheckUserLogin(QueryManager queryManager, AuthenticationManager authenticationManager) {
        this.queryManager = queryManager;
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        String inputEmail = req.getParameter("email");
        String inputPassword = req.getParameter("password");

        String response = "invalid";

        List users = queryManager.selectUserByEmail(inputEmail);

        if (users.size() != 0) {
            User user = (User) users.get(0);
            String userPassword = user.getPasswordHash();

            if (isPasswordMatch(inputPassword, userPassword)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                response = "valid";
            }
        }

        resp.getWriter().write(response);

    }

    private boolean isPasswordMatch(String inputPassword, String userPassword) {
        return authenticationManager.checkPassword(inputPassword, userPassword);
    }
}
