package com.example.consumer.event;

import com.example.model.Event;
import com.example.model.document.EventDocument;
import com.example.model.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventDocumentRepository eventDocumentRepository;
    private final EventEntityRepository eventEntityRepository;

    @Transactional("mongo")
    EventDocument saveDocument(Event event) {
        log.debug("saveDocument event={}", event);
        event.setSaved(System.currentTimeMillis());
        return eventDocumentRepository.save(new EventDocument(event));
    }

    @Transactional
    EventEntity saveEntity(Event event) {
        log.debug("saveEntity event={}", event);
        event.setSaved(System.currentTimeMillis());
        return eventEntityRepository.save(new EventEntity(event));
    }
}
