package com.example.consumer.event;

import com.example.model.Event;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@Setter
@Slf4j
@ConfigurationProperties(prefix = "event-consumer")
class EventConsumerConfig {

    Map<String, String> properties;

    @PostConstruct
    void init() {
        log.info("init properties={}", properties);
    }

    @Bean
    ConcurrentKafkaListenerContainerFactory<String, Event> eventListenerContainerFactory(PlatformTransactionManager transactionManager) {
        ConcurrentKafkaListenerContainerFactory<String, Event> factory = new ConcurrentKafkaListenerContainerFactory<>();
        ContainerProperties containerProperties = factory.getContainerProperties();
        containerProperties.setTransactionManager(transactionManager);
        containerProperties.setAckMode(ContainerProperties.AckMode.MANUAL);
        factory.setConsumerFactory(new DefaultKafkaConsumerFactory<>(properties.entrySet()
                .stream()
                .filter(e -> StringUtils.hasText(e.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))));
        return factory;
    }
}
