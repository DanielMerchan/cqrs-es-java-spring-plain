package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;

import java.util.List;

public interface EventStore {
    void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion);
    List<BaseEvent> getEvents(final String aggregateId);
}
