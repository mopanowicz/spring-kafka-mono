package com.example.monitor.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("counter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterDocument {
    @Id
    String id;
    long count;
}
