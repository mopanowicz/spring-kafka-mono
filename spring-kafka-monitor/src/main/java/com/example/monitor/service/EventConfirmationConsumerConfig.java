package com.example.monitor.service;

import com.example.model.EventConfirmation;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Setter
@ConfigurationProperties(prefix = "event-confirmation-consumer")
class EventConfirmationConsumerConfig {

    Map<String, String> properties;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, EventConfirmation> eventConfirmationListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, EventConfirmation> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, e -> String.valueOf(e.getValue())))));
        return factory;
    }
}
