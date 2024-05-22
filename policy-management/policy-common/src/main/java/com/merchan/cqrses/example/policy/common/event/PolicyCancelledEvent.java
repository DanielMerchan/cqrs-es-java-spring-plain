package com.merchan.cqrses.example.policy.common.event;

import com.merchan.cqrses.example.core.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class PolicyCancelledEvent extends BaseEvent {
}
