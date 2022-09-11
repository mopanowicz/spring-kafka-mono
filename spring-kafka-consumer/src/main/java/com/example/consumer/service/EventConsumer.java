package com.example.consumer.service;

import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final EventConfirmationProducer eventConfirmationProducer;

    @Value("${event-consumer.send-confirmation:false}")
    boolean sendConfirmation;

    @Transactional
    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key, @Payload Event event) {
        log.debug("receive key={} event={}", key, event);
        if (sendConfirmation) {
            eventConfirmationProducer.send(key, new EventConfirmation(event.getUuid(), LocalDateTime.now()));
        }
    }
}
