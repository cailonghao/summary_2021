package com.im.server;

import com.im.server.config.ImConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ImApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = SpringApplication.run(ImApplication.class, args);
        ImConfig.getInstance().initIm(ctx.getEnvironment().getProperty("im.port"));
    }
}
