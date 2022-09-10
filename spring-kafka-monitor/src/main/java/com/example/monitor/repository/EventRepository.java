package com.example.monitor.repository;

import com.example.monitor.document.EventDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventRepository extends MongoRepository<EventDocument, String> {
}
