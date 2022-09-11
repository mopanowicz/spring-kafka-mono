package com.example.monitor.repository;

import com.example.monitor.document.EventConfirmationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventConfirmationRepository extends MongoRepository<EventConfirmationDocument, String> {
    Optional<EventConfirmationDocument> findFirstByEventUuid(String eventUuid);
}
