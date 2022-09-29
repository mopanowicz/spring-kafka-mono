package com.example.consumer.event;

import com.example.model.document.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDocumentRepository extends MongoRepository<EventDocument, String> {
}
