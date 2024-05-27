package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;

public interface EventConsumer<T extends BaseEvent> {
    void accept(@Payload T event, Acknowledgment ack);
}
