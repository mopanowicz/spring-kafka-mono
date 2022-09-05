package com.example.consumer.service;

import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConsumer {

    private final EventConfirmationProducer eventConfirmationProducer;

    @Value("${event-consumer.send-confirmation:false}")
    boolean sendConfirmation;

    @KafkaListener(topics = "${event-consumer.topic}", containerFactory = "eventListenerContainerFactory")
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key, @Payload Event event) {
        log.debug("receive key={} event={}", key, event);
        event.setReceived(LocalDateTime.now());
        if (sendConfirmation) {
            eventConfirmationProducer.send(key, EventConfirmation.builder()
                    .originalEvent(event)
                    .build());
        }
    }
}
