package com.merchan.cqrses.example.policy.cmd.handler;

import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.policy.cmd.command.RenewPolicyCommand;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import org.springframework.stereotype.Service;

@Service
public class RenewPolicyCommandHandler implements CommandHandler<RenewPolicyCommand> {

    private final EventSourceHandler<PolicyAggregate> eventSourceHandler;

    public RenewPolicyCommandHandler(EventSourceHandler<PolicyAggregate> eventSourceHandler, EventSourceHandler<PolicyAggregate> eventSourceHandler1) {
        this.eventSourceHandler = eventSourceHandler1;
    }

    @Override
    public void handle(RenewPolicyCommand command) {
        var aggregate = eventSourceHandler.getById(command.getId());
        aggregate.renewPolicy(command.getRenewPeriod());
        eventSourceHandler.save(aggregate);
    }
}
