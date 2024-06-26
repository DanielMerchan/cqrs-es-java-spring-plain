package com.merchan.cqrses.example.policy.query.domain;

import com.merchan.cqrses.example.core.domain.BaseEntity;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Policy extends BaseEntity {
    @Id
    private String policyId;
    private String organizationId;
    private LocalDate startDate;
    private LocalDate endDate;
    private PolicyStatus status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Policy policy = (Policy) o;
        return Objects.equals(policyId, policy.policyId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(policyId, organizationId, startDate, endDate, status);
    }
}
