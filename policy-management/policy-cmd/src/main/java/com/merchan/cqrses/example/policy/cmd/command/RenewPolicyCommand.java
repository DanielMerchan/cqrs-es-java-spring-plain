package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class RenewPolicyCommand extends BaseCommand {
    private PolicyPeriod renewPeriod;
}