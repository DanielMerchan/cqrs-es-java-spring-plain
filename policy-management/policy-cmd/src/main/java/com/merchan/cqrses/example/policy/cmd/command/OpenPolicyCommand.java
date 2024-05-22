package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OpenPolicyCommand extends BaseCommand {
    private String organizationId; // Organisation associated to this policy
    private List<String> conditions;
    private LocalDate startDate;
    private PolicyPeriod policyPeriod;
}
