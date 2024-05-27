package com.merchan.cqrses.example.policy.cmd.handler;

import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.core.handler.EventSourceHandler;
import com.merchan.cqrses.example.policy.cmd.command.CreatePolicyCommand;
import com.merchan.cqrses.example.policy.cmd.domain.PolicyAggregate;
import org.springframework.stereotype.Service;

@Service
public class CreatePolicyCommandHandler implements CommandHandler<CreatePolicyCommand> {

    private final EventSourceHandler<PolicyAggregate> eventSourceHandler;

    public CreatePolicyCommandHandler(EventSourceHandler<PolicyAggregate> eventSourceHandler) {
        this.eventSourceHandler = eventSourceHandler;
    }

    @Override
    public void handle(CreatePolicyCommand command) {
        var aggregate = new PolicyAggregate(command);
        eventSourceHandler.save(aggregate);
    }
}
