package com.example.producer.service;

import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
class EventDeliveryMonitor {

    void eventSent(Event event) {
        log.debug("eventSent event={}", event);
    }

    void eventSendFailed(Event event) {
        log.warn("eventSendFailed event={}", event);
    }

    void eventConfirmationReceived(EventConfirmation eventConfirmation) {
        log.debug("eventConfirmationReceived eventConfirmation={}", eventConfirmation);
    }
}
