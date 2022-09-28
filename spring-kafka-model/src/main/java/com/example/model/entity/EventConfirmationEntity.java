package com.example.model.entity;

import com.example.model.EventConfirmation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;

@Entity(name = "event_confirmation")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventConfirmationEntity {
    @Id
    String eventId;
    Long eventReceived;

    public EventConfirmationEntity(EventConfirmation eventConfirmation) {
        this.eventId = eventConfirmation.getEventId();
        this.eventReceived = eventConfirmation.getEventReceived();
    }
}
