package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

public class CancelPolicyCommand extends BaseCommand {
    public CancelPolicyCommand(String id) {
        super(id);
    }
}
