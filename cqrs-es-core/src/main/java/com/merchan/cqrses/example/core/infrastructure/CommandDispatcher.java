package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.core.command.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    <T extends BaseCommand> void dispatch(T command) throws IllegalArgumentException;
}
