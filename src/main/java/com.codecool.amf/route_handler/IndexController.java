package com.codecool.amf.route_handler;

import com.codecool.amf.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
