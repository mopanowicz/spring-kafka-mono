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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CounterService {

    private final MongoTemplate mongoTemplate;

    private final CounterRepository counterRepository;

    public List<CounterDocument> getAll() {
        return counterRepository.findAll();
    }

    @Transactional
    public void reset(String counterId) {
        Query query = new Query(Criteria.where("id").is(counterId));
        Update update = new Update();
        update.set("count", 0);
        update.set("first", null);
        update.set("last", null);
        mongoTemplate.upsert(query, update, CounterDocument.class);
    }

    @Transactional
    public long count(String counterId) {
        LocalDateTime now = LocalDateTime.now();
        Query query = new Query(Criteria.where("id").is(counterId));
        Update update = new Update();
        update.inc("count", 1);
        update.set("last", now);
        UpdateResult result = mongoTemplate.upsert(query, update, CounterDocument.class);
        CounterDocument counter = counterRepository.findById(counterId).get();
        if (counter.getFirst() == null) {
            counter.setFirst(now);
            counterRepository.save(counter);
        }
        return counter.getCount();
    }
}
