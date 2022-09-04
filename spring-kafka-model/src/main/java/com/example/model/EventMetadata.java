package com.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class EventMetadata {
    String uuid;
    LocalDateTime created;
    LocalDateTime sent;
    LocalDateTime received;
}
