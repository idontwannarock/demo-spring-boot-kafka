package com.example.demospringbootkafka.listener;

import com.example.demospringbootkafka.producer.DemoOutput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DemoOutputTopicListener {

    @KafkaListener(id = "demo.output.listener", topics = "${kafka.topics.demo.output}", containerFactory = "batchContainerFactory")
    public void listen(
            @Payload List<Message<DemoOutput>> messages,
            Acknowledgment acknowledgment) {
        for (Message<DemoOutput> message : messages) {
            log.info("demo output receive: " + message.getPayload());
        }
        log.info("batch received message count: " + messages.size());
        acknowledgment.acknowledge();
    }
}
