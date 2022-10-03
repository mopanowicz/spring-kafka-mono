package com.example.consumer.event;

import com.example.model.Event;
import com.example.model.document.EventDocument;
import com.example.model.entity.EventEntity;
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
    void saveDocumentTest() {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        EventDocument saved = eventService.saveDocument(event);
        assertThat(saved.getId()).isNotNull();
    }

    @Test
    void saveEntityTest() {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        EventEntity saved = eventService.saveEntity(event);
        assertThat(saved.getId()).isNotNull();
    }
}
