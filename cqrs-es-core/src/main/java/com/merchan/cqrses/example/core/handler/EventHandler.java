package com.merchan.cqrses.example.core.handler;

import com.merchan.cqrses.example.core.event.BaseEvent;

public interface EventHandler<T extends BaseEvent> {
    void on(T event);
}
