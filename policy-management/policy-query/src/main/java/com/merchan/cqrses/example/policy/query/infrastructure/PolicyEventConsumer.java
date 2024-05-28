package com.merchan.cqrses.example.policy.query.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.handler.EventHandler;
import com.merchan.cqrses.example.core.infrastructure.EventConsumer;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.context.ApplicationContext;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class PolicyEventConsumer implements EventConsumer<BaseEvent> {

    private final ApplicationContext applicationContext;

    public PolicyEventConsumer(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @KafkaListener(topics ="${spring.kafka.consumer.policy.topic}", groupId = "${spring.kafka.consumer.group-id}")
    @Override
    public void accept(@Payload BaseEvent event, Acknowledgment ack) {
        String handlerBeanName = event.getClass().getSimpleName() + "Handler";
        EventHandler handler = applicationContext.getBean(handlerBeanName, EventHandler.class);
        handler.on(event);
        ack.acknowledge();
    }
}
