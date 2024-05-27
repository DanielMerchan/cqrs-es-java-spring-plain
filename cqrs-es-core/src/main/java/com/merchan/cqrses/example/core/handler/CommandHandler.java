package com.merchan.cqrses.example.core.handler;

import com.merchan.cqrses.example.core.command.BaseCommand;

@FunctionalInterface
public interface CommandHandler<T extends BaseCommand> {
    void handle(T command);
}
