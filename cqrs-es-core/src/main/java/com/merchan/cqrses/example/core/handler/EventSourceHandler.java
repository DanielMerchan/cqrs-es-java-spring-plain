package com.merchan.cqrses.example.core.handler;

import com.merchan.cqrses.example.core.domain.AggregateRoot;

public interface EventSourceHandler<T> {
    void save(AggregateRoot aggregate);
    T getById(String id);
    void republishEvents();
}
