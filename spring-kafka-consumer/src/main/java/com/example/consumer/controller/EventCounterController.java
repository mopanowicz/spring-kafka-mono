package com.example.consumer.controller;

import com.example.consumer.service.EventCounter;
import com.example.consumer.service.EventCounterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class EventCounterController {

    private final EventCounterService eventCounterService;

    @GetMapping("/event-counter")
    @ResponseBody
    EventCounter get() {
        return eventCounterService.getEventCounter();
    }

    @GetMapping("/event-counter-reset")
    @ResponseBody
    void reset() {
        eventCounterService.reset();
    }
}
