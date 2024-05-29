package com.merchan.cqrses.example.policy.query.handler.event;

import com.merchan.cqrses.example.core.handler.EventHandler;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.common.event.PolicyCreatedEvent;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

@Service("PolicyCreatedEventHandler")
public class PolicyCreatedEventHandler implements EventHandler<PolicyCreatedEvent> {

    private final PolicyRepository policyRepository;

    public PolicyCreatedEventHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void on(PolicyCreatedEvent event) {
        var policy = Policy.builder()
                .policyId(event.getId())
                .startDate(event.getStartDate())
                .organizationId(event.getOrganizationId())
                .endDate(event.getEndDate())
                .status(PolicyStatus.LIVE).build();
        policyRepository.save(policy);
    }
}
