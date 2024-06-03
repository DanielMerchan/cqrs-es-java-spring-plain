package com.merchan.cqrses.example.policy.common.event;

import com.merchan.cqrses.example.core.event.BaseEvent;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@SuperBuilder
public class PolicyCancelledEvent extends BaseEvent {
}
