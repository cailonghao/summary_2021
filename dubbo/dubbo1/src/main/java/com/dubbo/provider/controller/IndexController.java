package com.dubbo.provider.controller;

import com.dubbo.provider.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/imLogin")
    public String imLogin(@RequestParam String username,
                          @RequestParam String password) {
        return redisService.addUser(username);
    }
}
