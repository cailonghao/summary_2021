package com.frame.kafka.controller;

import com.frame.kafka.config.KafkaConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.common.KafkaFuture;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@RestController
@RequestMapping("/kafka")
public class KafkaController {

    @RequestMapping("/getAllTopic")
    public Object getAllTopic() throws ExecutionException, InterruptedException, TimeoutException {
        ListTopicsResult result = KafkaConfig.adminClient.listTopics();
        Set<String> topics = result.names().get(2, TimeUnit.SECONDS);
        return topics;
    }

}
