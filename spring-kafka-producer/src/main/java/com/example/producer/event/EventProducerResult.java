package com.example.producer.event;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
class EventProducerResult {
    int numberOfEvents;
    int cargoLength;
}
