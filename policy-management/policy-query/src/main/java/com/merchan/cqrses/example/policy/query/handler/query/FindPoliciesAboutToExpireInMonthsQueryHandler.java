package com.merchan.cqrses.example.policy.query.handler.query;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.policy.query.api.query.FindPoliciesAboutToExpireInMonths;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FindPoliciesAboutToExpireInMonthsQueryHandler implements QueryHandler<FindPoliciesAboutToExpireInMonths> {

    private final PolicyRepository policyRepository;

    public FindPoliciesAboutToExpireInMonthsQueryHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<BaseEntity> handle(FindPoliciesAboutToExpireInMonths query) {
        return policyRepository.findByEndDateBetween(LocalDate.now(), LocalDate.now().plusMonths(query.getNumberOfMonths())).stream()
                .map(policy -> (BaseEntity) policy)
                .toList();

    }
}
