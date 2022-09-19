package com.example.monitor.eventconfirmation;

import com.example.model.EventConfirmation;
import com.example.monitor.counter.CounterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConfirmationConsumer {

    private final EventConfirmationRepository eventConfirmationRepository;

    private final CounterService counterService;

    @Transactional
    @KafkaListener(topics = "${event-confirmation-consumer.topic}", containerFactory = "eventConfirmationListenerContainerFactory")
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key,
                 @Payload EventConfirmation eventConfirmation) {
        log.debug("receive key={} eventConfirmation={}", key, eventConfirmation);
        EventConfirmationDocument eventConfirmationDocument = new EventConfirmationDocument(eventConfirmation);
        eventConfirmationRepository.save(eventConfirmationDocument);
        counterService.count("eventConfirmation");
    }
}
