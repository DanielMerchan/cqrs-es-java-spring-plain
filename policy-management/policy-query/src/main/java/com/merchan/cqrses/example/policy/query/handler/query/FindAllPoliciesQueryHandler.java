package com.merchan.cqrses.example.policy.query.handler.query;


import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.policy.query.api.query.FindAllPolicies;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FindAllPoliciesQueryHandler implements QueryHandler<FindAllPolicies> {

    private final PolicyRepository policyRepository;

    public FindAllPoliciesQueryHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<BaseEntity> handle(FindAllPolicies query) {
        return policyRepository.findAll().stream().map(policy -> (BaseEntity) policy).toList();
    }
}

