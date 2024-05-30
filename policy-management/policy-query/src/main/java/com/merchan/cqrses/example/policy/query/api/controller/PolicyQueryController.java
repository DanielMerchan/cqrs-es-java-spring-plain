package com.merchan.cqrses.example.policy.query.api.controller;

import com.merchan.cqrses.example.core.infrastructure.QueryDispatcher;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.query.api.dto.PolicyQueryResponse;
import com.merchan.cqrses.example.policy.query.api.query.FindAllPolicies;
import com.merchan.cqrses.example.policy.query.api.query.FindPoliciesAboutToExpireInMonths;
import com.merchan.cqrses.example.policy.query.api.query.FindPoliciesByStatus;
import com.merchan.cqrses.example.policy.query.api.query.FindPolicyById;
import com.merchan.cqrses.example.policy.query.domain.Policy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/query/policies")
public class PolicyQueryController {
    private final Logger logger = Logger.getLogger(PolicyQueryController.class.getName());
    private final QueryDispatcher queryDispatcher;

    public PolicyQueryController(QueryDispatcher queryDispatcher) {
        this.queryDispatcher = queryDispatcher;
    }

    @GetMapping
    public ResponseEntity<PolicyQueryResponse> getAllPolicies() {
        try {
            List<Policy> policies = queryDispatcher.dispatch(new FindAllPolicies());
            if (policies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var response =  PolicyQueryResponse.builder()
                    .policies(policies)
                    .message(MessageFormat.format("Successfully return {0} policies",policies.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            var errorMessage = "Error occurred while getting policies";
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new PolicyQueryResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PolicyQueryResponse> getPolicyById(@PathVariable("id") String id) {
        try {
            List<Policy> policies = queryDispatcher.dispatch(new FindPolicyById(id));
            if (policies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var response =  PolicyQueryResponse.builder()
                    .policies(policies)
                    .message("Successfully return policy")
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error retrieving the policy {0}: ", id);
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new PolicyQueryResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<PolicyQueryResponse> getPoliciesByStatus(@PathVariable PolicyStatus status) {
        try {
            List<Policy> policies = queryDispatcher.dispatch(new FindPoliciesByStatus(status));
            if (policies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var response =  PolicyQueryResponse.builder()
                    .policies(policies)
                    .message(MessageFormat.format("Successfully return {0} policies",policies.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = "Error occurred while getting policies by status";
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new PolicyQueryResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/expiration/months/{months}")
    public ResponseEntity<PolicyQueryResponse> getPoliciesAboutToExpireInMonths(@PathVariable int months) {
        try {
            List<Policy> policies = queryDispatcher.dispatch(new FindPoliciesAboutToExpireInMonths(months));
            if (policies.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            var response =  PolicyQueryResponse.builder()
                    .policies(policies)
                    .message(MessageFormat.format("Successfully return {0} policies",policies.size()))
                    .build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error occurred while getting policies about to expire in {0} months", months);
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new PolicyQueryResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
