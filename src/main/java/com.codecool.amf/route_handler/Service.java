package com.codecool.amf.route_handler;

import com.codecool.amf.EmailSender;
import com.codecool.amf.jpa.PersistenceManager;
import com.codecool.amf.jpa.QueryManager;
import com.codecool.amf.model.HRequest;
import com.codecool.amf.model.Location;
import com.codecool.amf.model.PService;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.User;
import org.json.JSONObject;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.List;

public class Service extends HttpServlet {

    private final EmailSender emailSender;
    private final PersistenceManager persistenceManager;
    private final QueryManager queryManager;

    public Service(EmailSender emailSender, PersistenceManager persistenceManager, QueryManager queryManager) {
        this.emailSender = emailSender;
        this.persistenceManager = persistenceManager;
        this.queryManager = queryManager;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JSONObject requestJSON = getJsonObjectFromRequest(req);
        JSONObject service = (JSONObject) requestJSON.get("service_type");
        JSONObject address = (JSONObject) requestJSON.get("address");

        long time = getTime();
        Location location = createLocation(address);
        Partner requestedPartner = getPartner(service);
        User user = getUser(req);
        String locationLabel = address.getString("label");

        HRequest hRequest = new HRequest(time, requestedPartner, location, user, locationLabel);

        persistenceManager.persistEntity(hRequest);

        notifyPartner(service.getString("service"), hRequest);
        sendConfirmationForUser(service.getString("service"),hRequest);

        resp.getWriter().write("{\"valami\": 3}");
    }

    private void sendConfirmationForUser(String serviceType,HRequest hRequest) {
        String message = emailSender.createConfirmationMessage(hRequest);
        try {
            String subject = "Request " + hRequest.getCreationDate() + " confirmation";
            String userEmail = hRequest.getUser().getEmail();
            emailSender.send(userEmail, subject, message, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJsonObjectFromRequest(HttpServletRequest req) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(req.getInputStream()));
        String json = "";
        json = br.readLine();
        return new JSONObject(json.toString());
    }

    private void notifyPartner(String service, HRequest hRequest) {
        String msg = emailSender.createMsg(hRequest);

        try {
            String subject = "Request " + hRequest.getCreationDate();
            String partnerEmail = hRequest.getPartner().getEmail();
            String serviceType = service;

            emailSender.send(partnerEmail, subject, msg, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private User getUser(HttpServletRequest request) {
        User userDetails = (User) request.getSession().getAttribute("user");
        String userEmail = userDetails.getEmail();
        List users = queryManager.selectUserByEmail(userEmail);
        User user = (User) users.get(0);
        return user;
    }

    private Partner getPartner(JSONObject serviceJSON) {
        PService service = getPService(serviceJSON.getString("service"));
        List<Partner> partnerList = queryManager.selectPartnerByService(service);

        return partnerList.get(0);
    }

    private PService getPService(String typeOfService) {
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

    private Location createLocation(JSONObject address) {
        Location location = new Location(
                address.getString("country"),
                address.getString("locality"),
                address.getString("postal_code"),
                address.getString("route"),
                address.getString("street_number")
        );

        return location;
    }

    private long getTime() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        return timestamp.getTime();
    }
}