package com.example.monitor.service;

import com.example.model.Event;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.support.LogIfLevelEnabled;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Setter
@ConfigurationProperties(prefix = "event-consumer")
class EventConsumerConfig {

    Map<String, String> properties;

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Event> eventListenerContainerFactory(PlatformTransactionManager transactionManager) {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setTransactionManager(transactionManager);
        containerProperties.setAckMode(ContainerProperties.AckMode.RECORD);
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        return factory;
    }
}
