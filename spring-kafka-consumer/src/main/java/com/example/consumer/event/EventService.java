package com.example.consumer.event;

import com.example.model.Event;
import com.example.model.document.EventDocument;
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
    Event saveEvent(Event event) {
        log.debug("saveEvent event={}", event);
        event.setSaved(System.currentTimeMillis());
        eventRepository.save(new EventDocument(event));
        return event;
    }
}
