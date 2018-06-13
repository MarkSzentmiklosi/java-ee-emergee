package com.codecool.amf.route_handler;

import com.codecool.amf.authenticator.AuthenticationManager;
import com.codecool.amf.model.Address;
import com.codecool.amf.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

public class Registration extends HttpServlet {

    private AuthenticationManager authenticationManager;

    public Registration(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
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
            System.out.printf("Cannot create new address for new user.");
        }

        try {
            User newUser = createNewUser(registrationData, newUserAddress);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Cannot create new user.");
        }


    }

    private Address generateAddress(HashMap<String, String> data) {
        return new Address(
                data.get("country"),
                data.get("city"),
                data.get("zip"),
                data.get("street"),
                data.get("houseNumber")
        );
    }

    private User createNewUser(HashMap<String, String> data, Address address) {
        return new User(
                data.get("name"),
                data.get("email"),
                data.get("phoneNumber"),
                data.get("idNumber"),
                address
        );
    }
}
