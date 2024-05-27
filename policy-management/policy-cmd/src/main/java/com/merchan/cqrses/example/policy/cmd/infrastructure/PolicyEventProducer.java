package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.infrastructure.EventProducer;

public class PolicyEventProducer implements EventProducer<BaseEvent> {

    @Override
    public void produce(String topic, BaseEvent event) {

    }
}
