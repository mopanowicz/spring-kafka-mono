package com.example.consumer.event;

import com.example.consumer.eventconfirmation.EventConfirmationProducer;
import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final EventService eventService;
    private final EventConfirmationProducer eventConfirmationProducer;

    @Value("${event-consumer.send-confirmation:false}")
    boolean sendConfirmation;

    @Value("${event-consumer.save-event:false}")
    boolean saveEvent;

    @PostConstruct
    void init() {
        log.info("init sendConfirmation={} saveEvent={}", sendConfirmation, saveEvent);
    }

    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key,
                 @Payload Event event, Acknowledgment acknowledgment) {
        log.debug("receive key={} event={}", key, event);
        LocalDateTime received = LocalDateTime.now();
        if (saveEvent) {
            eventService.saveEvent(event, received);
        }
        if (sendConfirmation) {
            eventConfirmationProducer.send(key, new EventConfirmation(event.getUuid(), received.toInstant(ZoneOffset.UTC).toEpochMilli()));
        }
        acknowledgment.acknowledge();
    }
}
