package com.example.consumer.event;

import com.example.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EventServiceTest {

    @Autowired
    EventService eventService;

    @Test
    void saveTest() {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        Event saved = eventService.saveEvent(event);
        assertThat(saved.getId()).isNotNull();
    }
}
