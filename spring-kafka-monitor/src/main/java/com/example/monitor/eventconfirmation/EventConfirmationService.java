package com.example.monitor.eventconfirmation;

import com.example.model.Event;
import com.example.model.EventConfirmation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
@Slf4j
@RequiredArgsConstructor
public class EventConfirmationService {

    private final EventConfirmationRepository eventConfirmationRepository;

    @Transactional("transactionManager")
    EventConfirmationDocument saveEventConfirmation(EventConfirmation eventConfirmation) {
        log.debug("saveEventConfirmation eventConfirmation={} received={}", eventConfirmation);
        return eventConfirmationRepository.save(new EventConfirmationDocument(eventConfirmation));
    }
}
