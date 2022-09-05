package com.example.consumer.service;

import com.example.model.EventConfirmation;
import lombok.Setter;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.transaction.KafkaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Setter
@Configuration
@EnableTransactionManagement
@ConfigurationProperties(prefix = "event-confirmation-producer")
public class EventConfirmationProducerConfig {

    Map<String, String> properties;

    @Bean
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

    @Bean
    ProducerFactory<String, EventConfirmation> producerFactory() {
        return new DefaultKafkaProducerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue()))));
    }

    @Bean
    public KafkaTemplate<String, EventConfirmation> kafkaTemplate(ProducerFactory<String, EventConfirmation> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }
}
