package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
public class Event {
    String uuid;
    LocalDateTime created;
    LocalDateTime sent;
    Object cargo;
}
