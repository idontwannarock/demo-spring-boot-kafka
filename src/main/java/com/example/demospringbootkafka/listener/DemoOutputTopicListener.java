package com.example.demospringbootkafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DemoOutputTopicListener {

    @KafkaListener(id = "demo.output.listener", topics = "${kafka.topics.demo.output}", containerFactory = "batchContainerFactory")
    public void listen(List<String> messages, Acknowledgment acknowledgment) {
        for (String message : messages) {
            log.info("demo output receive: " + message);
        }
        log.info("batch received message count: " + messages.size());
        acknowledgment.acknowledge();
    }
}
