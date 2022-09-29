package com.example.consumer.event;

import com.example.model.entity.EventEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventEntityRepository extends CrudRepository<EventEntity, String> {
}
