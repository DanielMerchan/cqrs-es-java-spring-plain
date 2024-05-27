package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.infrastructure.EventProducer;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PolicyEventProducer implements EventProducer<BaseEvent> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PolicyEventProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void produce(String topic, BaseEvent event) {
            kafkaTemplate.send(topic, event);
    }
}
