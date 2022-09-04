package com.example.producer.controller;

import com.example.model.Event;
import com.example.producer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Setter
@RequiredArgsConstructor
class ProducerController {

    private final ProducerService producerService;

    @Value("${events.topic}")
    String eventsTopic;

    @GetMapping("/test-random-event")
    @ResponseBody
    Event testRandomEvent() {
        Event event = Event.builder()
                .uuid(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .build();
        producerService.send(eventsTopic, null, event);
        return event;
    }
}
