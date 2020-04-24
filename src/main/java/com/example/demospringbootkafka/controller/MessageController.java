package com.example.demospringbootkafka.controller;

import com.example.demospringbootkafka.producer.DemoInput;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Api(tags = "消息")
@RequestMapping("message")
@RestController
public class MessageController {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("demo/input")
    public ResponseEntity<?> createDemoInputs(@RequestBody Long totalInputs) {
        for (int i = 0; i < totalInputs; i++) {
            DemoInput input = new DemoInput();
            input.setInput("demo message " + (i + 1));
            kafkaTemplate.send("topic.demo.input", input);
        }
        return ResponseEntity.ok().build();
    }
}
