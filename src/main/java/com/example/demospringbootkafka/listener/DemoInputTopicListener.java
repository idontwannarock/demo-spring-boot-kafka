package com.example.demospringbootkafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoInputTopicListener {

    // 聲明 consumer id，監聽 topicName 為 topic.demo.input 的 topic
    @KafkaListener(id = "demo.input.listener", topics = "topic.demo.input")
    public void listen(String message) {
        log.info("demo input receive : " + message);
    }
}
