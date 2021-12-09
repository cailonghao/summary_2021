package com.im.server.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {

    @Value("${server.port}")
    private Integer port;
    @Value("${im.port}")
    private Integer imPort;

    @GetMapping("/index")
    public String getIndex(Model model) {
        model.addAttribute("port", port);
        System.out.println("port = " + port);
        return "index";
    }

    @GetMapping("/talk")
    public String getTalk(Model model) {
        model.addAttribute("port", port);
        model.addAttribute("imPort", imPort);
        return "talk";
    }
}
