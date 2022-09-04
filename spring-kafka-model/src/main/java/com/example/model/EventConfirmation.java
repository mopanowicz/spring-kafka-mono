package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EventConfirmation extends EventMetadata {

    LocalDateTime confirmed;

    @Builder
    public EventConfirmation(Event originalEvent) {
        super(originalEvent.getUuid(), originalEvent.getCreated(), originalEvent.getSent());
    }
}
