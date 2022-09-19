package com.example.monitor.event;

import com.example.model.Event;
import com.example.monitor.counter.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final CounterService counterService;

    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(ConsumerRecord<String, Event> record) {
        log.debug("receive record={}", record);
        counterService.count("event");
    }
}
