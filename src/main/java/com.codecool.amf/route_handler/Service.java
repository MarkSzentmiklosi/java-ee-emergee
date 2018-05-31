package com.codecool.amf.route_handler;

import com.codecool.amf.PService;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.HRequest;
import com.codecool.amf.model.Location;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@WebServlet(urlPatterns = {"/service"})
public class Service extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HashMap<String, String> requestDetails = getLocationAndServiceDetails(req);

        long time = getTime();
        Location location = createLocation(requestDetails);
        Partner requestedPartner = getPartner(requestDetails);
        User user = getUser(req);

        HRequest hRequest = new HRequest(time, requestedPartner, location, user);
    }

    User getUser(HttpServletRequest request) {
        return (User) request.getSession().getAttribute("user");
    }

    Partner getPartner(HashMap<String, String> requestDetails) {
        PService service = getPService(requestDetails);
        List<Partner> partnerList = QueryManager.selectPartnerByService(service);

        return partnerList.get(0);
    }

    PService getPService(HashMap<String, String> requestDetails) {
        String typeOfService = requestDetails.get("service");
        PService service = null;

        switch (typeOfService) {
            case "ambulance":
                service = PService.AMBULANCE;
                break;
            case "fire":
                service = PService.FIRE;
                break;
            case "police":
                service = PService.POLICE;
                break;
            case "repair":
                service = PService.CAR_REPAIR;
                break;
        }
        return service;
    }

    protected Location createLocation(HashMap<String, String> requestDetails) {
        Location location = new Location(
                requestDetails.get("country"),
                requestDetails.get("city"),
                requestDetails.get("zip"),
                requestDetails.get("street"),
                requestDetails.get("houseNum")
        );

        return location;
    }

    private long getTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return timestamp.getTime();
    }

    private HashMap<String, String> getLocationAndServiceDetails(HttpServletRequest req) {

        String[] detailKeys = {"service", "country", "city", "zip", "street", "houseNum"};
        HashMap<String, String> requestDetails = new HashMap<>();

        for (String key : detailKeys) {
            requestDetails.put(key, req.getParameter(key));
        }

        return requestDetails;
    }
}