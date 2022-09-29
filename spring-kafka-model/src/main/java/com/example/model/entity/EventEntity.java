package com.example.model.entity;

import com.example.model.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "event")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventEntity {
    @Id
    String id;
    Long sent;
    Long saved;
    Long received;
    String cargo;

    public EventEntity(Event event) {
        this.id = event.getId();
        this.sent = event.getSent();
        this.saved = event.getSaved();
        this.received = event.getReceived();
        this.cargo = event.getCargo();
    }
}
