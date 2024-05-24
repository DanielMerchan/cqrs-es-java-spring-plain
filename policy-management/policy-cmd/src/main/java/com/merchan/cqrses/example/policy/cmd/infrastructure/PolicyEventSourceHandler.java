package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.domain.AggregateRoot;
import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.core.infrastructure.EventStore;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import org.springframework.stereotype.Service;

import java.util.Comparator;

@Service
public class PolicyEventSourceHandler implements EventSourceHandler<PolicyAggregate> {

    private final EventStore eventStore;

    public PolicyEventSourceHandler(EventStore eventStore) {
        this.eventStore = eventStore;
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
}
