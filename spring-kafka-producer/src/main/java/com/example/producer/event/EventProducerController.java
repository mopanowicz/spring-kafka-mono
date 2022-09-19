package com.example.producer.event;

import com.example.model.Event;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Setter
@Slf4j
@RequiredArgsConstructor
class EventProducerController {

    private final EventProducer eventProducer;

    @Value("${event-producer-controller.uuidAsKey:false}")
    boolean uuidAsKey;

    @GetMapping("/produce-random-event")
    @ResponseBody
    EventProducerResult produceRandomEvent(
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents,
            @RequestParam(name = "cargoSize", defaultValue = "16", required = false) int cargoSize,
            @RequestParam(name = "cargoSLength", defaultValue = "32", required = false) int cargoSLength
    ) throws JsonProcessingException {
        for (int i = 0; i < numberOfEvents; i++) {
            Event event = randomEvent(cargoSize, cargoSLength);
            String key = null;
            if (uuidAsKey) {
                key = event.getUuid();
            }
            eventProducer.send(key, event);
        }
        return new EventProducerResult(numberOfEvents, cargoSize, cargoSLength);
    }

    Event randomEvent(int cargoSize, int cargoSLength) {
        Event.Cargo[] cargo = new Event.Cargo[cargoSize];

        Event event = new Event();
        event.setUuid(UUID.randomUUID().toString());
        event.setCargo(cargo);

        for (int i = 0; i < cargoSize; i++) {
            cargo[i] = new Event.Cargo();
            cargo[i].setI((int)(cargoSize * Math.random()));
            cargo[i].setD(cargoSize * Math.random());
            cargo[i].setS(RandomStringUtils.randomAlphanumeric(cargoSLength));
        }
        return event;
    }
}
