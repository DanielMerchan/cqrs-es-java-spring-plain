package com.merchan.cqrses.example.policy.cmd.api.dto;

import com.merchan.cqrses.example.policy.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreatePolicyResponse extends BaseResponse {
    private String policyId;
    public CreatePolicyResponse(String message, String policyId) {
        super(message);
        this.policyId = policyId;
    }
}
