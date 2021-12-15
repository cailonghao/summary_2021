package com.server.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "ms-order")
public interface OrderClient {

    @RequestMapping("/index/index")
    String index();

}
