package com.example.producer.event;

import com.example.model.Event;
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

    @Value("${event-producer-controller.id-as-key:false}")
    boolean idAsKey;

    @GetMapping("/produce-random-event")
    @ResponseBody
    EventProducerResult produceRandomEvent(
            @RequestParam(name = "numberOfEvents", defaultValue = "1", required = false) int numberOfEvents,
            @RequestParam(name = "cargoLength", defaultValue = "256", required = false) int cargoLength
    ) {
        for (int i = 0; i < numberOfEvents; i++) {
            Event event = randomEvent(cargoLength);
            String key = null;
            if (idAsKey) {
                key = event.getId();
            }
            eventProducer.send(key, event);
        }
        return EventProducerResult.builder()
                .numberOfEvents(numberOfEvents)
                .cargoLength(cargoLength)
                .build();
    }

    Event randomEvent(int cargoLength) {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        event.setCargo(RandomStringUtils.randomAlphanumeric(cargoLength));
        return event;
    }
}
