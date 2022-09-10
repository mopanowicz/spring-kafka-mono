package com.example.monitor.document;

import com.example.model.Event;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("event")
@ToString(callSuper = true)
public class EventDocument extends Event {

    @Id
    String id;

    public EventDocument(Event source) {
        super(source.getUuid(), source.getSent(), source.getCargo());
    }
}
