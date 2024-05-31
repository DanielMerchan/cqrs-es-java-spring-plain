package com.merchan.cqrses.example.policy.query.api.dto;

import com.merchan.cqrses.example.policy.common.dto.BaseResponse;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PolicyQueryResponse extends BaseResponse {
    private List<Policy> policies;

    public PolicyQueryResponse(String message) {
        super(message);
    }
}
