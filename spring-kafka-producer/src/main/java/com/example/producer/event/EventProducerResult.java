package com.example.producer.event;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class EventProducerResult {
    int numberOfEvents;
    int cargoSize;
    int cargoSLength;
}
