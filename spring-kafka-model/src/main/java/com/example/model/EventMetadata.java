package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public abstract class EventMetadata {
    String uuid;
    LocalDateTime created;
    LocalDateTime sent;
}
