package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;

public interface EventProducer<T extends BaseEvent> {
    void produce(String topic, T event);
}
