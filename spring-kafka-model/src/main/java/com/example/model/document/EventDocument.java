package com.example.model.document;

import com.example.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDocument {
    @Id
    String id;
    Long sent;
    Long saved;
    Long received;
    String cargo;

    public EventDocument(Event event) {
        this.id = event.getId();
        this.sent = event.getSent();
        this.saved = event.getSaved();
        this.received = event.getReceived();
        this.cargo = event.getCargo();
    }
}
