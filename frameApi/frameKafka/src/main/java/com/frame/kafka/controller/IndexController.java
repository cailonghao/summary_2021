package com.frame.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/index")
public class IndexController {

    @RequestMapping("/zone")
    public Object t1() {
        log.info("hehe zone");
        return 1 / 0;
    }


    @RequestMapping("/null")
    public Object t2(String str) {
        log.info("hehe null");
        List<String> list = new ArrayList<>();
        list.add(str);
        return "";
    }
}
