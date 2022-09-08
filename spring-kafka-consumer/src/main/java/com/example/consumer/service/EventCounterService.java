package com.example.consumer.service;

import com.example.model.Event;
import lombok.Getter;
import org.springframework.stereotype.Service;

@Service
@Getter
public class EventCounterService {

    EventCounter eventCounter = new EventCounter();

    public int countEvent(Event event) {
        return eventCounter.increaseNumberOfEvents();
    }

    public void reset() {
        eventCounter.reset();
    }
}
