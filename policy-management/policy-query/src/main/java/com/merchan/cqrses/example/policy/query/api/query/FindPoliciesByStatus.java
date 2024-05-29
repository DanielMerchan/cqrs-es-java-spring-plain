package com.merchan.cqrses.example.policy.query.api.query;

import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.core.query.BaseQuery;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
public class FindPoliciesByStatus extends BaseQuery {
    private PolicyStatus status;
}
