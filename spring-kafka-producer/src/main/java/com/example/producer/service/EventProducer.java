package com.example.producer.service;

import com.example.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class EventProducer {

    private final EventDeliveryMonitor eventDeliveryMonitor;
    private final KafkaTemplate<String, Event> kafkaTemplateTransactional;

    @Value("${event-producer.topic}")
    String topic;

    @Value("${event-producer.blocking}")
    boolean blocking;

    ListenableFutureCallback<SendResult<String, Event>> sendCallback;

    @PostConstruct
    void init() {
        sendCallback = new ListenableFutureCallback<>() {

            @Override
            public void onSuccess(final SendResult<String, Event> result) {
                eventDeliveryMonitor.eventSent(result.getProducerRecord().value());
            }

            @Override
            public void onFailure(final Throwable ex) {
                log.error("onFailure", ex);
            }
        };
    }

    @SneakyThrows
    @Transactional
    public void send(String key, Event event) {
        log.debug("send key={} event={}", key, event);
        event.setSent(LocalDateTime.now());
        ListenableFuture future = kafkaTemplateTransactional.send(topic, key, event);
        future.addCallback(sendCallback);
        if (blocking) {
            future.get();
        }
    }
}
