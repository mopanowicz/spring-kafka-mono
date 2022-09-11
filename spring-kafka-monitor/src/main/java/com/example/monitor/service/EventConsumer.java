package com.example.monitor.service;

import com.example.model.Event;
import com.example.monitor.document.EventDocument;
import com.example.monitor.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final EventRepository eventRepository;

    private final CounterService counterService;

    @Value("${event-consumer.send-confirmation:false}")
    boolean sendConfirmation;

    @Transactional
    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key, @Payload Event event) {
        log.debug("receive key={} event={}", key, event);
        EventDocument eventDocument = new EventDocument(event);
        eventRepository.save(eventDocument);
        counterService.countEvent();
    }
}
