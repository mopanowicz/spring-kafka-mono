package com.example.monitor.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Document("counter")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterDocument {
    @Id
    String id;
    long count;
    LocalDateTime first;
    LocalDateTime last;

    public float getCountsPerSecond() {
        float countsPerSecond = 0;
        if (last != null && first != null && count > 0) {
            float seconds = last.toInstant(ZoneOffset.UTC).getEpochSecond() - first.toInstant(ZoneOffset.UTC).getEpochSecond();
            countsPerSecond = count / seconds;
        }
        return countsPerSecond;
    }
}
