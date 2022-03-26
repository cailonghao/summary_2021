package com.cloud.app;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class AppStart {


    public static void main(String[] args) {
        SpringApplication.run(AppStart.class, args);
        log.info("port = {}", 12000);
    }
}
