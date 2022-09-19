package com.example.consumer.event;

import com.example.consumer.event.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface EventRepository extends MongoRepository<EventDocument, String> {
    Optional<EventDocument> findFirstByUuid(String uuid);
}
