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

    @KafkaListener(
            id = "demo.output.listener",
            idIsGroup = false,
            topics = "${kafka.topics.demo.output}",
            containerFactory = "batchContainerFactory")
    public void listen(
            @Payload List<Message<DemoOutput>> messages,
            Acknowledgment acknowledgment) {
        for (Message<DemoOutput> message : messages) {
            String partition = String.valueOf(message.getHeaders().get("kafka_receivedPartitionId"));
            String offset = String.valueOf(message.getHeaders().get("kafka_offset"));
            String groupId = String.valueOf(message.getHeaders().get("kafka_groupId"));
            log.info("Partition " + partition + " offset " + offset + " group " + groupId + ": " + message.getPayload().getOutput());
        }
//        log.info("batch received message count: " + messages.size());
        acknowledgment.acknowledge();
    }
}
