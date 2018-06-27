package com.codecool.amf.controller;

import com.codecool.amf.service.HelpRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;


@RestController
public class HelpRequestHandler {

    @Autowired
    private HelpRequestService helpRequestService;


    @PostMapping(value = "/service")
    public String handleHelpRequest(@RequestBody String json, HttpSession session) {
        return helpRequestService.handleHelpRequestPost(json, session);
    }


}
