package com.kfk.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KfkListener {


    @KafkaListener(id = "listen1", topics = "topic1")
    public void listen1(String msg) {
        System.out.println("topic1 ===> " + msg);
    }

    @KafkaListener(id = "listen2", topics = "kft")
    public void listen2(String msg) {
        System.out.println("topic2 ===> " + msg);
    }
}
