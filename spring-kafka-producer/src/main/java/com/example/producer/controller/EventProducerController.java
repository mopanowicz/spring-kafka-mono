package com.example.producer.controller;

import com.example.model.Event;
import com.example.producer.service.EventProducer;
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
class EventProducerController {

    private final EventProducer eventProducer;

    @Value("${event-producer-controller.uuidAsKey:false}")
    boolean uuidAsKey;

    @GetMapping("/test-random-event")
    @ResponseBody
    Event testRandomEvent() {
        Event event = Event.builder()
                .uuid(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .build();

        String key = null;
        if (uuidAsKey) {
            key = event.getUuid();
        }

        eventProducer.send(key, event);
        return event;
    }
}
