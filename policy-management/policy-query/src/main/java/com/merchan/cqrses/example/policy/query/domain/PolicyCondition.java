package com.merchan.cqrses.example.policy.query.domain;

import jakarta.persistence.*;
import lombok.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PolicyCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String conditionId;
    private String conditionCode;
    @ManyToOne(fetch = FetchType.LAZY)
    private Policy policy;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PolicyCondition that = (PolicyCondition) o;
        return Objects.equals(conditionId, that.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conditionId, conditionCode, policy);
    }
}
