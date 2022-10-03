package com.example.consumer.eventconfirmation;

import com.example.model.EventConfirmation;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Setter
@Slf4j
@ConfigurationProperties(prefix = "event-confirmation-producer")
public class EventConfirmationProducerConfig {

    Map<String, String> properties;


    @PostConstruct
    void init() {
        log.info("init properties={}", properties);
    }

    @Bean
    @Qualifier("kafka")
    KafkaTransactionManager kafkaTransactionManager(ProducerFactory<String, EventConfirmation> producerFactoryTransactional) {
        return new KafkaTransactionManager<>(producerFactoryTransactional);
    }

    @Bean
    ProducerFactory<String, EventConfirmation> producerFactoryTransactional() {
        Map<String, Object> configs = properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())));
        configs.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "event-confirmation-tx-");
        return new DefaultKafkaProducerFactory<>(configs);
    }

    @Bean
    public KafkaTemplate<String, EventConfirmation> kafkaTemplateTransactional(ProducerFactory<String, EventConfirmation> producerFactoryTransactional) {
        return new KafkaTemplate<>(producerFactoryTransactional);
    }
}
