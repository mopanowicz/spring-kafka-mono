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

    Cargo[] cargo;
    LocalDateTime received;

    @Builder
    public Event(String uuid, LocalDateTime created, LocalDateTime sent, Cargo[] cargo, LocalDateTime received) {
        super(uuid, created, sent, received);
        this.cargo = cargo;
    }

    @Data
    public static class Cargo {
        double d;
        int i;
        String s;
    }
}