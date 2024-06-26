package com.merchan.cqrses.example.policy.common.event;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PolicyCreatedEvent extends BaseEvent {
    private String organizationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate createdDate;
    private PolicyStatus status;
}
