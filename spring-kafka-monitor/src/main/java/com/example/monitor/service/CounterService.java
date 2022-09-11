package com.example.monitor.service;

import com.example.monitor.document.CounterDocument;
import com.example.monitor.repository.CounterRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final MongoTemplate mongoTemplate;

    private final CounterRepository counterRepository;

    public long countEvent() {
        return count("event");
    }

    public long countEventConfirmation() {
        return count("eventConfirmation");
    }

    long count(String counterId) {
        Query query = new Query(Criteria.where("id").is(counterId));
        Update update = new Update();
        update.inc("count", 1);
        UpdateResult result = mongoTemplate.upsert(query, update, CounterDocument.class);
        Optional<CounterDocument> counter = counterRepository.findById(counterId);
        return counter.get().getCount();
    }
}
