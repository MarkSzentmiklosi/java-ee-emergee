package com.codecool.amf.controller;

import com.codecool.amf.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @Autowired
    IndexService indexService;

    @GetMapping("/")
    public String renderIndex(HttpSession session) {
        return indexService.handleIndexGet(session);
    }

}
