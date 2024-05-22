package com.merchan.cqrses.example.policy.common.event;

import com.merchan.cqrses.example.core.event.BaseEvent;
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
public class PolicyRenewedEvent extends BaseEvent {
    private PolicyPeriod policyPeriod;
}
