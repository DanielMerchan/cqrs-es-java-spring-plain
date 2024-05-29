package com.merchan.cqrses.example.policy.query.handler.query;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.policy.query.api.query.FindPoliciesByStatus;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPoliciesByStatusHandler implements QueryHandler<FindPoliciesByStatus> {

    private final PolicyRepository policyRepository;

    public FindPoliciesByStatusHandler(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<BaseEntity> handle(FindPoliciesByStatus query) {
        return policyRepository.findByStatus(query.getStatus()).stream()
                .map(policy -> (BaseEntity) policy)
                .toList();
    }
}
