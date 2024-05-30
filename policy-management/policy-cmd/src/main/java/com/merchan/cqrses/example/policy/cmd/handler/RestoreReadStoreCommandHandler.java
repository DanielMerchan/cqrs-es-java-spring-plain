package com.merchan.cqrses.example.policy.cmd.handler;

import com.merchan.cqrses.example.core.handler.CommandHandler;
import com.merchan.cqrses.example.policy.cmd.command.RestoreReadStoreCommand;
import com.merchan.cqrses.example.policy.cmd.infrastructure.PolicyEventSourceHandler;
import org.springframework.stereotype.Service;

@Service
public class RestoreReadStoreCommandHandler implements CommandHandler<RestoreReadStoreCommand> {

    private final PolicyEventSourceHandler policyEventSourceHandler;

    public RestoreReadStoreCommandHandler(PolicyEventSourceHandler policyEventSourceHandler) {
        this.policyEventSourceHandler = policyEventSourceHandler;
    }

    @Override
    public void handle(RestoreReadStoreCommand command) {
        policyEventSourceHandler.republishEvents();
    }
}
