package com.merchan.cqrses.example.policy.cmd.handler;

import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.policy.cmd.command.SuspendPolicyCommand;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import org.springframework.stereotype.Service;

@Service
public class SuspendPolicyCommandHandler implements CommandHandler<SuspendPolicyCommand> {

    private final EventSourceHandler<PolicyAggregate> eventSourceHandler;

    public SuspendPolicyCommandHandler(EventSourceHandler<PolicyAggregate> eventSourceHandler, EventSourceHandler<PolicyAggregate> eventSourceHandler1) {
        this.eventSourceHandler = eventSourceHandler1;
    }

    @Override
    public void handle(SuspendPolicyCommand command) {
        var aggregate = eventSourceHandler.getById(command.getId());
        aggregate.suspendPolicy();
        eventSourceHandler.save(aggregate);
    }
}
