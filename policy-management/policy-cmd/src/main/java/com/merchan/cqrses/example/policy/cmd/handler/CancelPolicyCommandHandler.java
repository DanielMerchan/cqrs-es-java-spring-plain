package com.merchan.cqrses.example.policy.cmd.handler;

import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.policy.cmd.command.CancelPolicyCommand;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import org.springframework.stereotype.Service;

@Service
public class CancelPolicyCommandHandler implements CommandHandler<CancelPolicyCommand> {

    private final EventSourceHandler<PolicyAggregate> eventSourceHandler;

    public CancelPolicyCommandHandler(EventSourceHandler<PolicyAggregate> eventSourceHandler, EventSourceHandler<PolicyAggregate> eventSourceHandler1) {
        this.eventSourceHandler = eventSourceHandler1;
    }

    @Override
    public void handle(CancelPolicyCommand command) {
        var aggregate = eventSourceHandler.getById(command.getId());
        aggregate.cancelPolicy();
        eventSourceHandler.save(aggregate);
    }
}
