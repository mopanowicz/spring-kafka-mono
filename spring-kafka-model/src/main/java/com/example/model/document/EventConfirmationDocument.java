package com.example.model.document;

import com.example.model.EventConfirmation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("eventConfirmation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventConfirmationDocument {
    @Id
    String eventId;
    Long eventReceived;

    public EventConfirmationDocument(EventConfirmation eventConfirmation) {
        this.eventId = eventConfirmation.getEventId();
        this.eventReceived = eventConfirmation.getEventReceived();
    }
}
