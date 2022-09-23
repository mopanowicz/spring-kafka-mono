package com.example.monitor.counter;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<CounterDocument, String> {
}
