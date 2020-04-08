package com.example.demospringbootkafka.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoInputTopicListenerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Test
    public void testDemo() throws InterruptedException {
        kafkaTemplate.send("topic.demo.input", "this is my first demo");
        // 休眠 5 秒，為了使監聽器有足夠的時間監聽到 topic 的 message
        Thread.sleep(5000);
    }
}
