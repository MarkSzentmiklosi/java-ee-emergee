package com.codecool.amf.service;

import com.codecool.amf.model.HelpRequest;
import com.codecool.amf.model.Location;
import com.codecool.amf.model.Partner;
import com.codecool.amf.model.ServiceType;
import com.codecool.amf.model.User;
import com.codecool.amf.repository.HelpRequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;


@Service
public class HelpRequestService {

    @Autowired
    HelpRequestRepository helpRequestRepository;
    @Autowired
    private EmailService emailService;
    @Autowired
    private UserService userService;
    @Autowired
    private PartnerService partnerService;


    public void saveHelpRequest(HelpRequest helpRequest) {
        helpRequestRepository.save(helpRequest);
    }

    public String handleHelpRequestPost(String json, HttpSession session) {
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

        emailService.notifyPartner(requestedServiceType, helpRequest);
        emailService.sendConfirmationForUser(requestedServiceType, helpRequest);

        return "{\"status\": \"OK\" }";
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
