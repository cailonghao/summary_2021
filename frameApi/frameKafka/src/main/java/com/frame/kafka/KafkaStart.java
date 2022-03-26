package com.frame.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class KafkaStart {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(KafkaStart.class, args);
    }

}
