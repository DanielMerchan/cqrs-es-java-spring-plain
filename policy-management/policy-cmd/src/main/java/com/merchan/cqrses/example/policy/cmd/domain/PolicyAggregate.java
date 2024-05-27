package com.merchan.cqrses.example.policy.cmd.domain;

import com.merchan.cqrses.example.core.domain.AggregateRoot;
import com.merchan.cqrses.example.policy.cmd.command.CreatePolicyCommand;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.common.event.PolicyCancelledEvent;
import com.merchan.cqrses.example.policy.common.event.PolicyCreatedEvent;
import com.merchan.cqrses.example.policy.common.event.PolicyRenewedEvent;
import com.merchan.cqrses.example.policy.common.event.PolicySuspendedEvent;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
public class PolicyAggregate extends AggregateRoot {

    private PolicyStatus policyStatus;
    private LocalDate endDate;

    public PolicyAggregate(CreatePolicyCommand command) {
        if (command.getStartDate().isAfter(command.getEndDate()) || command.getEndDate().isEqual(command.getStartDate())) {
            throw new IllegalStateException("Start date cannot be after end date");
        }
        raiseEvent(PolicyCreatedEvent.builder()
                .id(command.getId())
                .organizationId(command.getOrganizationId())
                .conditions(List.copyOf(command.getConditions()))
                .startDate(command.getStartDate())
                .endDate(command.getEndDate())
                .createdDate(LocalDate.now())
                .build());
    }

    public void apply(PolicyCreatedEvent event) {
        this.id = event.getId();
        this.policyStatus = PolicyStatus.LIVE;
        this.endDate = event.getEndDate();
    }

    public void cancelPolicy() {
        switch (this.policyStatus) {
            case CANCELLED -> throw new IllegalStateException("Policy is already cancelled");
            case EXPIRED -> throw new IllegalStateException("Policy is expired");
        }
        raiseEvent(PolicyCancelledEvent.builder()
                .id(this.id)
                .build());
    }

    public void apply(PolicyCancelledEvent event) {
        this.id = event.getId();
        this.policyStatus = PolicyStatus.CANCELLED;
    }

    public void suspendPolicy() {
        switch(this.policyStatus) {
            case SUSPENDED -> throw new IllegalStateException("Policy is already suspended");
            case CANCELLED -> throw new IllegalStateException("Policy is cancelled");
            case EXPIRED -> throw new IllegalStateException("Policy is expired");
        }

        raiseEvent(PolicySuspendedEvent.builder().id(this.id).build());
    }

    public void apply(PolicySuspendedEvent event) {
        this.id = event.getId();
        this.policyStatus = PolicyStatus.SUSPENDED;
    }

    public void renewPolicy(PolicyPeriod policyPeriod) {
        if (this.policyStatus != PolicyStatus.LIVE) {
            throw new IllegalStateException("A non-live policy cannot be renewed");
        }
        raiseEvent(PolicyRenewedEvent.builder()
                .id(this.getId())
                .policyPeriod(policyPeriod)
                .endDate(this.endDate)
                .build());
    }

    public void apply(PolicyRenewedEvent event) {
        this.id = event.getId();
        PolicyPeriod renewPeriod = event.getPolicyPeriod();
        switch (renewPeriod) {
            case SIX_MONTHS -> this.endDate = event.getEndDate().plusMonths(6);
            case YEAR -> this.endDate = event.getEndDate().plusYears(1);
            case TWO_YEAR -> this.endDate = event.getEndDate().plusYears(2);
            default -> this.endDate = event.getEndDate();
        }
    }
}
