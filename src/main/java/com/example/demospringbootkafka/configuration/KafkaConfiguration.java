package com.example.demospringbootkafka.configuration;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConfiguration {

    @Value("#{'${spring.kafka.bootstrap-servers}'.split(',')}")
    private List<String> bootstrapServers;
    @Value("${spring.kafka.consumer.group-id}")
    private String consumerGroupId;

    // ConcurrentKafkaListenerContainerFactory 為創建 Kafka 監聽器的工程類，這裡只配置了消費者
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    // 根據 consumerProps 填寫的參數創建消費者工廠
    public ConsumerFactory<String, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

    // 消費者配置參數
    private Map<String, Object> consumerProps() {
        Map<String, Object> props = new HashMap<>();
        // Kafka broker 位址
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // GroupId
        props.put(ConsumerConfig.GROUP_ID_CONFIG, consumerGroupId);
        // 是否自動提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        // 自動提交的頻率，單位為 millisecond
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "100");
        // Session 超時設定，單位為 millisecond，設定提供 liveness 資訊給 broker 的間隔
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "15000");
        // 鍵的反序列化方式
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        // 值的反序列化方式
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        return props;
    }

    // kafkaTemplate 實作 Kafka 發送接收等功能
    @Bean
    public KafkaTemplate<String, String> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // 根據 senderProps 填寫的參數創建生產者工廠
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(senderProps());
    }

    // 生產者配置
    private Map<String, Object> senderProps () {
        Map<String, Object> props = new HashMap<>();
        // Kafka broker 位址
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        // 重試次數，0 為不啟用重試機制
        props.put(ProducerConfig.RETRIES_CONFIG, 1);
        // 控制批次處理大小控制，單位為 byte 位元組
//        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 102400);
        // 批次發送，延遲為 1 毫秒，啟用該功能可以有效減少生產者發送消息的次數，從而提高併發量
//        props.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        // 生產者可以使用的總內存位元組來緩衝等待發送到 broker 的紀錄
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1024000);
        // 鍵的序列化方式
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        // 值的序列化方式
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        return props;
    }
}
