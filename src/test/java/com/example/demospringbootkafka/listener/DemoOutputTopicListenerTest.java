package com.example.demospringbootkafka.listener;

import com.example.demospringbootkafka.producer.DemoInput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoOutputTopicListenerTest {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Test
    public void testBatchListen() throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            DemoInput input = new DemoInput();
            input.setInput("demo message " + (i + 1));
            kafkaTemplate.send("topic.demo.input", input);
        }
        // 休眠 5 秒，為了使監聽器有足夠的時間監聽到 topic 的 message
        Thread.sleep(5000);
    }
}
