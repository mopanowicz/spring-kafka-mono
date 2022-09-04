package com.example.producer.controller;

import com.example.model.Event;
import com.example.producer.service.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Setter
@RequiredArgsConstructor
class ProducerController {

    private final ProducerService producerService;

    @Value("${kafka.events-topic}")
    String eventsTopic;

    @GetMapping("/test")
    void test() {
        producerService.send(eventsTopic, null, Event.builder().created(LocalDateTime.now()).build());
    }
}
