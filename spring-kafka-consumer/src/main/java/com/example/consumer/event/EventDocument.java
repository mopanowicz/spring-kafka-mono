package com.example.consumer.event;

import com.example.model.Event;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document("event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EventDocument extends Event {

    @Id
    String id;

    @Indexed
    Long received;

    public EventDocument(Event source, Long received) {
        super(source.getUuid(), source.getSent()/*, source.getCargo()*/);
        this.received = received;
    }
}
