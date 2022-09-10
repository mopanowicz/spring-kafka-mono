package com.example.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Event {
    String uuid;
    LocalDateTime sent;
    Cargo[] cargo;

    @Data
    public static class Cargo {
        double d;
        int i;
        String s;
    }
}
