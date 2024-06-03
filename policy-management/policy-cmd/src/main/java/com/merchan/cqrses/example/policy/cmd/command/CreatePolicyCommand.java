package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreatePolicyCommand extends BaseCommand {
    private String organizationId; // Organisation associated to this policy
    private LocalDate startDate;
    private LocalDate endDate;
}
