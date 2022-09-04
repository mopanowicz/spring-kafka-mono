package com.example.producer.service;

import com.example.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

@Service
@Setter
@RequiredArgsConstructor
public class ProducerService {

    private final KafkaTemplate<String, Event> kafkaTemplate;

    @Value("${kafka.producer.blocking:false}")
    boolean blocking;

    @SneakyThrows
    public void send(String topic, String key, Event event) {
        event.setSent(LocalDateTime.now());
        ListenableFuture future = kafkaTemplate.send(topic, key, event);
        if (blocking) {
            future.get();
        }
    }
}
