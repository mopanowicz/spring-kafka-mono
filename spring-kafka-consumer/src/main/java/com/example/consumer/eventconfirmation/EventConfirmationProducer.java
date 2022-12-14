package com.example.consumer.eventconfirmation;

import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Setter
@Slf4j
@RequiredArgsConstructor
public class EventConfirmationProducer {

    private final KafkaTemplate<String, EventConfirmation> kafkaTemplateTransactional;

    @Value("${event-confirmation-producer.topic}")
    String topic;
    @Value("${event-confirmation-producer.blocking}")
    boolean blocking;

    @SneakyThrows
    @Transactional("kafkaTransactionManager")
    public void send(String key, EventConfirmation eventConfirmation) {
        log.debug("send key={} event={}", key, eventConfirmation);
        ListenableFuture future = kafkaTemplateTransactional.send(topic, key, eventConfirmation);
        if (blocking) {
            future.get();
        }
    }
}
