package com.merchan.cqrses.example.policy.query.handler.query;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.policy.query.api.query.FindPoliciesByOrganizationId;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindPoliciesByOrganizationIdQueryHandler implements QueryHandler<FindPoliciesByOrganizationId> {

    private final PolicyRepository policyRepository;

    public FindPoliciesByOrganizationIdQueryHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<BaseEntity> handle(FindPoliciesByOrganizationId query) {
        return policyRepository.findByOrganizationId(query.getOrganisationId()).stream()
                .map(policy -> (BaseEntity) policy)
                .toList();
    }
}
