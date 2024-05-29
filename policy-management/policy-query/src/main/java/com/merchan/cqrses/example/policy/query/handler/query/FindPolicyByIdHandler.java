package com.merchan.cqrses.example.policy.query.handler.query;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.core.handler.QueryHandler;
import com.merchan.cqrses.example.policy.query.api.query.FindPolicyById;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FindPolicyByIdHandler implements QueryHandler<FindPolicyById> {

    private final PolicyRepository policyRepository;

    public FindPolicyByIdHandler(final PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public List<BaseEntity> handle(FindPolicyById query) {
        List<BaseEntity> findPolicyByIdReply = new ArrayList<>();
        Optional<Policy> optPolicy = policyRepository.findById(query.getPolicyId());
        optPolicy.ifPresent(findPolicyByIdReply::add);
        return findPolicyByIdReply;
    }
}
