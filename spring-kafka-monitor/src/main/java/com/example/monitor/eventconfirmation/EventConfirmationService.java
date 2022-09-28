package com.example.monitor.eventconfirmation;

import com.example.model.EventConfirmation;
import com.example.model.document.EventConfirmationDocument;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConfirmationService {

    private final EventConfirmationDocumentRepository eventConfirmationDocumentRepository;

    @Transactional("transactionManager")
    EventConfirmation saveEventConfirmation(EventConfirmation eventConfirmation) {
        log.debug("saveEventConfirmation eventConfirmation={}", eventConfirmation);
        eventConfirmationDocumentRepository.save(new EventConfirmationDocument(eventConfirmation));
        return eventConfirmation;
    }
}
