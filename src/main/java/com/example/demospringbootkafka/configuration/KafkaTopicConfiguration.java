package com.example.demospringbootkafka.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfiguration {

    @Bean
    public NewTopic demoInputTopic() {
        return new NewTopic("topic.demo.input", 1, (short) 1);
    }
}
