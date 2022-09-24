package com.example.monitor.eventconfirmation;

import com.example.model.EventConfirmation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
public class EventConfirmationServiceTest {

    @Autowired
    EventConfirmationService eventConfirmationService;

    @Test
    void saveTest() {
        EventConfirmationDocument saved = eventConfirmationService.saveEventConfirmation(new EventConfirmation());
        assertThat(saved.id).isNotNull();
    }
}
