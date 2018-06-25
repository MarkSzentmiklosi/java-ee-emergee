package com.codecool.amf.route_handler;

import com.codecool.amf.EmailSender;
import com.codecool.amf.model.HelpRequest;
import com.codecool.amf.model.Location;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.User;
import com.codecool.amf.service.HelpRequestService;
import com.codecool.amf.service.PartnerService;
import com.codecool.amf.service.UserService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@RestController
public class HelpRequestHandler {

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;
    @Autowired
    private HelpRequestService helpRequestService;


    @RequestMapping(value = "/service", method = RequestMethod.POST)
    public String handleHelpRequest(@RequestBody String json, HttpSession session) {
        JSONObject requestJSON = new JSONObject(json);
        JSONObject serviceJson = (JSONObject) requestJSON.get("service_type");
        String requestedServiceType = serviceJson.getString("service");
        JSONObject address = (JSONObject) requestJSON.get("address");

        User userInSession = (User) session.getAttribute("user");

        long time = getTime();
        Location location = createLocation(address);
        Partner requestedPartner = partnerService.selectPartnerByServiceType(getPService(requestedServiceType));
        User userInDB = userService.getUserByEmail(userInSession.getEmail());
        String locationLabel = address.getString("label");

        HelpRequest helpRequest = new HelpRequest(time, requestedPartner, location, userInDB, locationLabel);

        helpRequestService.saveHelpRequest(helpRequest);

        notifyPartner(requestedServiceType, helpRequest);
        sendConfirmationForUser(requestedServiceType, helpRequest);

        return "success";
    }

    private void sendConfirmationForUser(String serviceType, HelpRequest helpRequest) {
        String message = emailSender.createConfirmationMessage(helpRequest);
        try {
            String subject = "Request " + helpRequest.getCreationDate() + " confirmation";
            String userEmail = helpRequest.getUser().getEmail();
            emailSender.send(userEmail, subject, message, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private void notifyPartner(String service, HelpRequest helpRequest) {
        String msg = emailSender.createMsg(helpRequest);

        try {
            String subject = "Request " + helpRequest.getCreationDate();
            String partnerEmail = helpRequest.getPartner().getEmail();
            String serviceType = service;

            emailSender.send(partnerEmail, subject, msg, serviceType);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    private ServiceType getPService(String typeOfService) {
        ServiceType service = null;

        switch (typeOfService) {
            case "ambulance":
                service = ServiceType.AMBULANCE;
                break;
            case "fire":
                service = ServiceType.FIRE;
                break;
            case "police":
                service = ServiceType.POLICE;
                break;
            case "repair":
                service = ServiceType.CAR_REPAIR;
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
