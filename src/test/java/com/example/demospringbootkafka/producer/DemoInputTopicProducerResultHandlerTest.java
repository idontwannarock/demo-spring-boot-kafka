package com.example.demospringbootkafka.producer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DemoInputTopicProducerResultHandlerTest {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private DemoInputTopicProducerResultHandler producerListener;

    @Test
    public void testProducerListen() throws InterruptedException {
        kafkaTemplate.setProducerListener(producerListener);
        kafkaTemplate.send("topic.demo.input", "test producer listen");
        Thread.sleep(1000);
    }

    @Test
    public void testSyncSend() throws ExecutionException, InterruptedException {
        kafkaTemplate.send("topic.demo.input", "test sync send message").get();
    }

    @Test(expected = TimeoutException.class)
    public void testTimeOut() throws ExecutionException, InterruptedException, TimeoutException {
        kafkaTemplate.send("topic.demo.input", "test send message timeout").get(1, TimeUnit.MICROSECONDS);
    }
}
