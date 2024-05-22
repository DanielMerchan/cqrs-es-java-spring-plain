package com.merchan.cqrses.example.core.command;

import com.merchan.cqrses.example.core.message.Message;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
