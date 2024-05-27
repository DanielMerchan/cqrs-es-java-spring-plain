package com.merchan.cqrses.example.core.infrastructure;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.core.handler.CommandHandler;

public interface CommandDispatcher {
    <T extends BaseCommand> void registerHandler(Class<T> type, CommandHandler<T> handler);
    <T extends BaseCommand> void dispatch(T command) throws IllegalArgumentException;
}
