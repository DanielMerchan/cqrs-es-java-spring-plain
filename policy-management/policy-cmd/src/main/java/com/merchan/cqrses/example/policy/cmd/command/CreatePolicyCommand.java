package com.merchan.cqrses.example.policy.cmd.command;

import com.merchan.cqrses.example.core.command.BaseCommand;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class CreatePolicyCommand extends BaseCommand {
    private String organizationId; // Organisation associated to this policy
    private LocalDate startDate;
    private LocalDate endDate;
}
