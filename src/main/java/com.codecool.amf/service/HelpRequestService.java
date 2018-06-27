package com.codecool.amf.service;

import com.codecool.amf.EmailSender;
import com.codecool.amf.model.*;
import com.codecool.amf.repository.HelpRequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.mail.MessagingException;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@Service
public class HelpRequestService {

    @Autowired
    HelpRequestRepository helpRequestRepository;
    @Autowired
    private EmailSender emailSender;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;


    public void saveHelpRequest(HelpRequest helpRequest) {
        helpRequestRepository.save(helpRequest);
    }

    public String handleHelpRequestPost(@RequestBody String json, HttpSession session) {
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

        saveHelpRequest(helpRequest);

        notifyPartner(requestedServiceType, helpRequest);
        sendConfirmationForUser(requestedServiceType, helpRequest);

        return "{\"status\": \"OK\" }";
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
