package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event extends EventMetadata {

    Object cargo;

    @Builder
    public Event(String uuid, LocalDateTime created, LocalDateTime sent, Object cargo) {
        super(uuid, created, sent);
        this.cargo = cargo;
    }
}
