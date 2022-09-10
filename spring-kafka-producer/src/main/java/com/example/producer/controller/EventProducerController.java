package com.example.producer.controller;

import com.example.model.Event;
import com.example.producer.service.EventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@Setter
@Slf4j
@RequiredArgsConstructor
class EventProducerController {

    private final EventProducer eventProducer;
    private final ObjectMapper objectMapper;

    @Value("${event-producer-controller.uuidAsKey:false}")
    boolean uuidAsKey;

    @GetMapping("/produce-random-event")
    @ResponseBody
    Event produceRandomEvent(
            @RequestParam(name = "cargoSize", defaultValue = "16", required = false) int cargoSize,
            @RequestParam(name = "cargoSLength", defaultValue = "32", required = false) int cargoSLength
    ) throws JsonProcessingException {
        Event event = randomEvent(cargoSize, cargoSLength);

        String key = null;
        if (uuidAsKey) {
            key = event.getUuid();
        }

        eventProducer.send(key, event);

        String stringEvent = objectMapper.writeValueAsString(event);

        log.info("produceRandomEvent length={}", stringEvent.length());

        return event;
    }

    Event randomEvent(int cargoSize, int cargoSLength) {
        Event.Cargo[] cargo = new Event.Cargo[cargoSize];

        Event event = Event.builder()
                .uuid(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .cargo(cargo)
                .build();

        for (int i = 0; i < cargoSize; i++) {
            cargo[i] = new Event.Cargo();
            cargo[i].setI((int)(cargoSize * Math.random()));
            cargo[i].setD(cargoSize * Math.random());
            cargo[i].setS(RandomStringUtils.randomAlphanumeric(cargoSLength));
        }
        return event;
    }
}
