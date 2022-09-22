package com.example.monitor.eventconfirmation;

import com.example.model.EventConfirmation;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("eventConfirmation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EventConfirmationDocument extends EventConfirmation {

    @Id
    String id;

    public EventConfirmationDocument(EventConfirmation source) {
        super(source.getEventUuid(), source.getEventReceived());
    }
}
