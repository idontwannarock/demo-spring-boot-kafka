package com.example.demospringbootkafka.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DemoInputTopicListener {

    @Value("${kafka.topics.demo.output")
    private String demoOutputTopicName;

    // 聲明 listener id，監聽 topicName 為 topic.demo.input 的 topic
    @KafkaListener(id = "demo.input.listener", topics = "${kafka.topics.demo.input}")
    public Message<String> forward(String input) {
        log.info("demo input receive : " + input);
        return MessageBuilder
                .withPayload(input)
                .setHeader(KafkaHeaders.TOPIC, demoOutputTopicName)
                .build();
    }
}
