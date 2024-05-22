package com.merchan.cqrses.example.policy.cmd.infrastructure;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.core.command.CommandHandlerMethod;
import com.merchan.cqrses.example.core.infrastructure.CommandDispatcher;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PolicyCommandDispatcher implements CommandDispatcher {

    private final Map<Class<? extends BaseCommand>, CommandHandlerMethod<? extends BaseCommand>> handlers = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler) {
        handlers.computeIfAbsent(type, c -> handler);
    }

    @Override
    public <T extends BaseCommand> void dispatch(T command) throws IllegalArgumentException {
        CommandHandlerMethod<T> handler = (CommandHandlerMethod<T>) handlers.get(command.getClass());
        if (handler == null) {
            throw new IllegalArgumentException("No handler found for command: " + command.getClass().getName());
        }
        handler.handle(command);
    }
}
