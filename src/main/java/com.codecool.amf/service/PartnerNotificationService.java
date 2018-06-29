package com.codecool.amf.service;

import com.codecool.amf.model.HelpRequest;
import com.codecool.amf.repository.HelpRequestRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartnerNotificationService {

    @Autowired
    HelpRequestRepository helpRequestRepository;

    public String handleNotifyPartner(String id) {
        try {
            Long longId = Long.parseLong(id);
            HelpRequest helpRequest = helpRequestRepository.findById(longId).get();
            return buildJson(helpRequest);

        } catch (Exception e) {
            System.out.println(e);
        }
        return new JSONObject().put("status", "error").toString();
    }

    private String buildJson(HelpRequest helpRequest) {
        String jsonString = new JSONObject()
                .put("partner_id", helpRequest.getPartner().getId())
                .put("request_location", helpRequest.getLocationLabel())
                .put("creationDate", helpRequest.getCreationDate())
                .put("user_name", helpRequest.getUser().getName())
                .put("user_phone_number", helpRequest.getUser().getPhoneNumber())
                .put("status", "ok")
                .toString();

        return jsonString;
    }
}
