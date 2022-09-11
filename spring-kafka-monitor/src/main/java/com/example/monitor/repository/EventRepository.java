package com.example.monitor.repository;

import com.example.monitor.document.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository<EventDocument, String> {
    Optional<EventDocument> findFirstByUuid(String uuid);
}
