package com.merchan.cqrses.example.policy.query.infrastructure;

import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PolicyRepository extends JpaRepository<Policy, String> {
    List<Policy> findByStatus(PolicyStatus status);
}
