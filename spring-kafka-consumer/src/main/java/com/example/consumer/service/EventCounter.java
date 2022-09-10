package com.example.consumer.service;

import lombok.Data;

@Data
public class EventCounter {

    long firstEvent;
    long lastEvent;
    int numberOfEvents;

    public int increaseNumberOfEvents() {
        long now = System.currentTimeMillis();
        if (firstEvent == 0) {
            firstEvent = now;
        }
        lastEvent = now;
        return ++numberOfEvents;
    }

    public double getEventsPerSecond() {
        double eps = 0;
        double seconds = (lastEvent - firstEvent) / 1000.0;
        if (seconds > 0) {
            eps =  numberOfEvents / seconds;
        }
        return eps;
    }

    public void reset() {
        firstEvent = 0;
        lastEvent = 0;
        numberOfEvents = 0;
    }
}
