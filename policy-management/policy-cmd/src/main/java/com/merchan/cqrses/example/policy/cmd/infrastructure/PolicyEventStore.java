package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.event.EventModel;
import com.merchan.cqrses.example.core.exception.AggregateNotFoundException;
import com.merchan.cqrses.example.core.infrastructure.EventProducer;
import com.merchan.cqrses.example.core.infrastructure.EventStore;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import com.merchan.cqrses.example.policy.common.exception.ConcurrencyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.time.Instant;
import java.util.List;

@Service
public class PolicyEventStore implements EventStore {

    private final EventStoreRepository eventStoreRepository;
    private final EventProducer<BaseEvent> eventProducer;

    @Value("${spring.kafka.producer.policy.topic}")
    private String policyEventsTopic;

    public PolicyEventStore(EventStoreRepository eventStoreRepository, EventProducer<BaseEvent> eventProducer) {
        this.eventStoreRepository = eventStoreRepository;
        this.eventProducer = eventProducer;
    }

    @Override
    public void saveEvents(String aggregateId, Iterable<BaseEvent> events, int expectedVersion) {
        var eventList = eventStoreRepository.findByAggregateId(aggregateId);
        if (expectedVersion != -1 && eventList.getLast().getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        var version = expectedVersion;
        for (var event : events) {
            version++;
            event.setVersion(version);
            var eventModel = EventModel.builder()
                    .timestamp(Instant.now())
                    .aggregateId(aggregateId)
                    .aggregateType(PolicyAggregate.class.getTypeName())
                    .version(version)
                    .eventType(event.getClass().getTypeName())
                    .eventData(event)
                    .build();
            eventStoreRepository.save(eventModel);
            eventProducer.produce(policyEventsTopic, event);
        }

    }

    @Override
    public List<BaseEvent> getEvents(String aggregateId) {
        var eventList = eventStoreRepository.findByAggregateId(aggregateId);
        if (eventList == null || eventList.isEmpty()) {
            throw new AggregateNotFoundException(MessageFormat.format("Aggregate not found {0}:", aggregateId));
        }
        return eventList.stream().map(EventModel::getEventData).toList();
    }

    @Override
    public List<String> getAggregateIds() {
        var eventList = eventStoreRepository.findAll();
        if (eventList.isEmpty()) {
            throw new IllegalStateException("Event list is empty");
        }
        return eventList.stream().map(EventModel::getAggregateId).toList();
    }
}
