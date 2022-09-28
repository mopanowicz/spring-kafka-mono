package com.example.monitor.eventconfirmation;

import com.example.model.Event;
import com.example.model.EventConfirmation;
import com.example.model.document.EventConfirmationDocument;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EventConfirmationServiceTest {

    @Autowired
    EventConfirmationService eventConfirmationService;

    @Test
    void saveTest() {
        Event event = new Event();
        event.setId(UUID.randomUUID().toString());
        EventConfirmation saved = eventConfirmationService.saveEventConfirmation(new EventConfirmation(event));
        assertThat(saved.getEventId()).isNotNull();
    }
}
