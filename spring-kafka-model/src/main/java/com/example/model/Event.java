package com.example.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Event {
    String uuid;
    long timestamp;
}
