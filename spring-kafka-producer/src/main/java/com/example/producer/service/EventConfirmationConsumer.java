package com.example.producer.service;

import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConfirmationConsumer {

    private final EventDeliveryMonitor eventDeliveryMonitor;

    @KafkaListener(topics = "${event-confirmation-consumer.topic}", containerFactory = "eventConfirmationListenerContainerFactory")
    @Transactional
    void receive(@Header(value = KafkaHeaders.RECEIVED_MESSAGE_KEY, required = false) String key,
                 @Payload EventConfirmation eventConfirmation) {
        log.debug("receive key={} eventConfirmation={}", key, eventConfirmation);
        eventConfirmation.setConfirmationReceived(LocalDateTime.now());
        eventDeliveryMonitor.eventConfirmationReceived(eventConfirmation);
    }
}
