package com.example.monitor.counter;

import com.example.monitor.counter.CounterDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<CounterDocument, String> {
}
