package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

public class SuspendPolicyCommand extends BaseCommand {
    public SuspendPolicyCommand(String id) {
        super(id);
    }
}
