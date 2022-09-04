package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event extends EventMetadata {

    Object cargo;
    LocalDateTime received;

    @Builder
    public Event(String uuid, LocalDateTime created, LocalDateTime sent, Object cargo, LocalDateTime received) {
        super(uuid, created, sent, received);
        this.cargo = cargo;
    }
}
