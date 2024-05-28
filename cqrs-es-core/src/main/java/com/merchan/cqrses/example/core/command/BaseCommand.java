package com.merchan.cqrses.example.core.command;

import com.merchan.cqrses.example.core.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
