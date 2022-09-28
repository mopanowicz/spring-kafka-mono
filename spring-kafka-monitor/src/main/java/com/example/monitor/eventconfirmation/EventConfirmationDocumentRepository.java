package com.example.monitor.eventconfirmation;

import com.example.model.document.EventConfirmationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventConfirmationDocumentRepository extends MongoRepository<EventConfirmationDocument, String> {
    Optional<EventConfirmationDocument> findFirstByEventId(String eventId);
}
