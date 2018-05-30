package com.codecool.amf.route_handler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = {"/service"})
public class Service extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, String> requestDetails = getLocationAndService(req);

    }

    private HashMap<String, String> getLocationAndService(HttpServletRequest req) {

        String[] detailKeys = {"service", "country", "city", "zip", "street", "houseNum"};
        HashMap<String, String> requestDetails = new HashMap<>();

        for (String key : detailKeys) {
            requestDetails.put(key, req.getParameter(key));
        }

        return requestDetails;
    }
}