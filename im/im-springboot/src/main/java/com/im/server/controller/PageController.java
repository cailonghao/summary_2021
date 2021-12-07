package com.im.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class PageController {


    @GetMapping("/index")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/talk")
    public String getTalk() {
        return "talk";
    }
}
