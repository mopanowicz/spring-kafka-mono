package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventConfirmation extends EventMetadata {

    LocalDateTime confirmationSent;
    LocalDateTime confirmationReceived;

    @Builder
    public EventConfirmation(Event originalEvent) {
        super(originalEvent.uuid, originalEvent.created, originalEvent.sent, originalEvent.received);
    }
}
