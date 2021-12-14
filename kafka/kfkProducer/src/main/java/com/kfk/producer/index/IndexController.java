package com.kfk.producer.index;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.callback.Callback;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private KafkaTemplate<String, String> template;

    @RequestMapping("/index")
    public String index() {
        template.send("topic1", "test");
        return "ok";
    }



}
