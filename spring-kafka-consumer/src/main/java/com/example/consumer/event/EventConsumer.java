package com.example.consumer.event;

import com.example.consumer.eventconfirmation.EventConfirmationProducer;
import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final EventService eventService;
    private final EventConfirmationProducer eventConfirmationProducer;

    @Value("${event-consumer.send-confirmation:false}")
    boolean sendConfirmation;

    @PostConstruct
    void init() {
        log.info("init sendConfirmation={}", sendConfirmation);
    }

    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(ConsumerRecord<String, Event> record, Acknowledgment acknowledgment) {
        log.debug("receive record={}", record);
        Event event = record.value();
        event.setReceived(System.currentTimeMillis());
        eventService.saveEvent(event);
        if (sendConfirmation) {
            eventConfirmationProducer.send(record.key(), new EventConfirmation(event));
        }
        acknowledgment.acknowledge();
    }
}
