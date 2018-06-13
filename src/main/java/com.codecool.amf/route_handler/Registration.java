package com.codecool.amf.route_handler;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;

public class Registration extends HttpServlet {

    private AuthenticationManager authenticationManager;
    private PersistenceManager persistenceManager;

    public Registration(AuthenticationManager authenticationManager, PersistenceManager persistenceManager) {
        this.authenticationManager = authenticationManager;
        this.persistenceManager = persistenceManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] fieldKeys = {
                "name",
                "email",
                "password",
                "phoneNumber",
                "idNumber",
                "country",
                "city",
                "zip",
                "street",
                "houseNumber"
        };

        HashMap<String, String> registrationData = new HashMap<>();

        for (String key : fieldKeys) {
            if (key.equals("password")) {
                String hashedPassword = authenticationManager.hashPassword(req.getParameter(key));
                registrationData.put(key, hashedPassword);
                continue;
            }
            registrationData.put(key, req.getParameter(key));
        }

        Address newUserAddress = null;
        try {
            newUserAddress = generateAddress(registrationData);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot create new address for new user.");
        }

        User newUser = null;
        try {
            newUser = createNewUser(registrationData, newUserAddress);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot create new user.");
        }

        try {
            persistenceManager.persistEntity(newUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            HttpSession session = req.getSession();
            session.setAttribute("user", newUser != null ? newUser.getEmail() : null);
            resp.sendRedirect("/");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.printf("New user cannot log in.");
        }

    }

    Address generateAddress(HashMap<String, String> data) {
        return new Address(
                data.get("country"),
                data.get("city"),
                data.get("zip"),
                data.get("street"),
                data.get("houseNumber")
        );
    }

    User createNewUser(HashMap<String, String> data, Address address) {
        return new User(
                data.get("name"),
                data.get("email"),
                data.get("phoneNumber"),
                data.get("idNumber"),
                address
        );
    }
}
