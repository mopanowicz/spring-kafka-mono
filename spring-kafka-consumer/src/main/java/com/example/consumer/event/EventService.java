package com.example.consumer.event;

import com.example.model.Event;
import com.example.model.document.EventDocument;
import com.example.model.entity.EventEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventService {

    private final EventDocumentRepository eventDocumentRepository;
    private final EventEntityRepository eventEntityRepository;

    @Value("${event-service.save-event-document:false}")
    boolean saveEventDocument;
    @Value("${event-service.save-event-entity:false}")
    boolean saveEventEntity;

    @PostConstruct
    void init() {
        log.info("init saveEventDocument={} saveEventEntity={}", saveEventDocument, saveEventEntity);
    }

    @Transactional("transactionManager")
    Event saveEvent(Event event) {
        log.debug("saveEvent event={}", event);
        event.setSaved(System.currentTimeMillis());
        if (saveEventDocument) {
            eventDocumentRepository.save(new EventDocument(event));
        }
        if (saveEventEntity) {
            eventEntityRepository.save(new EventEntity(event));
        }
        return event;
    }
}
