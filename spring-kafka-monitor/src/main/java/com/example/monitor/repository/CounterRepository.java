package com.example.monitor.repository;

import com.example.monitor.document.CounterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<CounterDocument, String> {
}
