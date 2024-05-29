package com.merchan.cqrses.example.policy.query.infrastructure;

import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface PolicyRepository extends JpaRepository<Policy, String> {
    List<Policy> findByStatus(PolicyStatus status);
    Optional<Policy> findByOrganizationId(String organizationId);
    List<Policy> findByEndDateBetween(LocalDate startDate, LocalDate endDate);

}
