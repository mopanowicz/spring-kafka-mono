package com.example.consumer.event;

import com.example.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional("transactionManager")
    void saveEvent(Event event, LocalDateTime received) {
        eventRepository.save(new EventDocument(event, received != null ? received.toInstant(ZoneOffset.UTC).toEpochMilli() : null));
    }
}
