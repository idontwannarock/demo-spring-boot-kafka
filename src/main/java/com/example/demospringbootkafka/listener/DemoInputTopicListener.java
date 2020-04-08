package com.example.demospringbootkafka.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DemoInputTopicListener {

    private static final Logger log = LoggerFactory.getLogger(DemoInputTopicListener.class);

    // 聲明 consumer id，監聽 topicName 為 topic.demo.input 的 topic
    @KafkaListener(id = "demo.input.listener", topics = "topic.demo.input")
    public void listen(String message) {
        log.info("demo input receive : " + message);
    }
}
