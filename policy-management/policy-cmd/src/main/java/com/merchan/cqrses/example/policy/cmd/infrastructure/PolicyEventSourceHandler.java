package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.domain.AggregateRoot;
import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.core.infrastructure.EventStore;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PolicyEventSourceHandler implements EventSourceHandler<PolicyAggregate> {

    private final EventStore eventStore;
    private final PolicyEventProducer policyEventProducer;
    @Value("${spring.kafka.producer.policy.topic}")
    private String policyEventsTopic;

    public PolicyEventSourceHandler(EventStore eventStore, PolicyEventProducer policyEventProducer) {
        this.eventStore = eventStore;
        this.policyEventProducer = policyEventProducer;
    }

    @Override
    public void save(AggregateRoot aggregate) {
        eventStore.saveEvents(aggregate.getId(), aggregate.getUncommittedChanges(), aggregate.getVersion());
        aggregate.markChangesAsCommitted();
    }

    @Override
    public PolicyAggregate getById(String id) {
        var aggregate = new PolicyAggregate();
        var events = eventStore.getEvents(id);
        if (!events.isEmpty()) {
            aggregate.replayEvents(events);
            var latestVersion = events.stream().map(BaseEvent::getVersion).max(Comparator.naturalOrder());
            aggregate.setVersion(latestVersion.get());
        }
        return aggregate;
    }

    @Override
    public void republishEvents() {
        var aggregateIds = eventStore.getAggregateIds();
        for (var aggregateId : aggregateIds) {
            var aggregate = getById(aggregateId);
            if (aggregate == null || aggregate.getPolicyStatus() == PolicyStatus.CANCELLED) continue;
            var events = eventStore.getEvents(aggregateId);
            for (var event : events) {
                policyEventProducer.produce(policyEventsTopic, event);
            }
        }
    }
}
