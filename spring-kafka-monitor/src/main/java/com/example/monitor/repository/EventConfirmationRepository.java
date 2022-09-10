package com.example.monitor.repository;

import com.example.monitor.document.EventConfirmationDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventConfirmationRepository extends MongoRepository<EventConfirmationDocument, String> {
}