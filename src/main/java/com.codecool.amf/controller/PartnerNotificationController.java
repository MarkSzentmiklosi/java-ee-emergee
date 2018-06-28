package com.codecool.amf.controller;

import com.codecool.amf.service.PartnerNotificationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

@Controller
public class PartnerNotificationController {

    @Autowired
    PartnerNotificationService partnerNotificationService;

    @MessageMapping("/notifyPartner")
    @SendTo("/observeRequest")
    public String notifyPartner(String id) throws Exception {
        String helpRequestJson = partnerNotificationService.handleNotifyPartner(id);

        return helpRequestJson;
    }

}
