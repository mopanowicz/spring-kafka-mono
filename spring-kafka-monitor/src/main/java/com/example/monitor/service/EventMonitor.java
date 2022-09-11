package com.example.monitor.service;

import com.example.monitor.document.EventConfirmationDocument;
import com.example.monitor.document.EventDocument;
import com.example.monitor.repository.EventConfirmationRepository;
import com.example.monitor.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventMonitor {

    private final EventRepository eventRepository;
    private final EventConfirmationRepository eventConfirmationRepository;

    @Transactional
    void processEvent(EventDocument event) {
        log.debug("processEvent {}", event);
        Optional<EventConfirmationDocument> o = eventConfirmationRepository.findFirstByEventUuid(event.getUuid());
        if (o.isPresent()) {

        }
    }

    @Transactional
    void processEventConfirmation(EventConfirmationDocument eventConfirmation) {
        log.debug("processEventConfirmation {}", eventConfirmation);
        Optional<EventDocument> o = eventRepository.findFirstByUuid(eventConfirmation.getEventUuid());
        if (o.isEmpty()) {
            log.warn("no event for confirmation {}", eventConfirmation.getId());
        } else {
        }
    }
}
