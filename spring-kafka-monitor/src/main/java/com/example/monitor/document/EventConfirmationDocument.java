package com.example.monitor.document;

import com.example.model.EventConfirmation;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("eventConfirmation")
@ToString(callSuper = true)
public class EventConfirmationDocument extends EventConfirmation {

    @Id
    String id;

    public EventConfirmationDocument(EventConfirmation source) {
        super(source.getEventUuid(), source.getEventReceived());
    }
}
