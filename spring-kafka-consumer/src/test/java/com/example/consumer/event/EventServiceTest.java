package com.example.consumer.event;

import com.example.model.Event;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EventServiceTest {

    @Autowired
    EventService eventService;

    @Test
    void saveTest() {
        EventDocument saved = eventService.saveEvent(new Event(), LocalDateTime.now());
        assertThat(saved.id).isNotNull();
    }
}
