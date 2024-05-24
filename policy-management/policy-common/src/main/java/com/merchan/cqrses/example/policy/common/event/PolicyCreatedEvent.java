package com.merchan.cqrses.example.policy.common.event;

import com.merchan.cqrses.example.core.event.BaseEvent;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class PolicyCreatedEvent extends BaseEvent {
    private String organizationId;
    private List<String> conditions;
    private LocalDate startDate;
    private LocalDate endDate;
    private PolicyPeriod policyPeriod;
    private LocalDate createdDate;
}
