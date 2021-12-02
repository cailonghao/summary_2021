package com.dubbo.provider;

import com.dubbo.provider.config.ImServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class ProviderApp {
    public static ConfigurableApplicationContext ctx;
    public static void main(String[] args) {

        ctx = SpringApplication.run(ProviderApp.class, args);
        ImServerConfig.init();
    }


}
