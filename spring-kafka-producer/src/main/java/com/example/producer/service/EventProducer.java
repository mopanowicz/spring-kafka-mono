package com.example.producer.service;

import com.example.model.Event;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Setter
@Slf4j
@ConfigurationProperties(prefix = "event-producer")
public class EventProducer {

    String topic;
    boolean blocking;
    Map<String, String> properties;

    KafkaTemplate<String, Event> kafkaTemplate;

    @PostConstruct
    void init() {
        kafkaTemplate = new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())))));
    }

    @SneakyThrows
    public void send(String key, Event event) {
        log.debug("send key={} event={}", key, event);
        event.setSent(LocalDateTime.now());
        ListenableFuture future = kafkaTemplate.send(topic, key, event);
        if (blocking) {
            future.get();
        }
    }
}
