package com.server.user.controller;

import com.server.user.aspect.BackAop;
import com.server.user.client.OrderClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RequestMapping("/index")
@RestController
public class IndexController {

    @Resource
    private OrderClient orderClient;

    @BackAop(name = "测试")
    @RequestMapping("/index")
    public String index(@RequestParam String message,
                        @RequestParam String sign) {
        log.info("user module ... ");
        return orderClient.index();
    }


}
