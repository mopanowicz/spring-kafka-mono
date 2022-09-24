package com.example.consumer.event;

import com.example.model.Event;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventRepository eventRepository;

    @Transactional("transactionManager")
    EventDocument saveEvent(Event event, LocalDateTime received) {
        log.debug("saveEvent event={} received={}", event, received);
        return eventRepository.save(new EventDocument(event, received != null ? received.toInstant(ZoneOffset.UTC).toEpochMilli() : null));
    }
}
