package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class RenewPolicyCommand extends BaseCommand {
    private PolicyPeriod renewPeriod;
}