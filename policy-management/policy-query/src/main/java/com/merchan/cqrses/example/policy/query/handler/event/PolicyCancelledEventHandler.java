package com.merchan.cqrses.example.policy.query.handler.event;

import com.merchan.cqrses.example.core.handler.EventHandler;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.common.event.PolicyCancelledEvent;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Logger;

@Service("PolicyCancelledEventHandler")
public class PolicyCancelledEventHandler implements EventHandler<PolicyCancelledEvent> {

    private final PolicyRepository policyRepository;

    private final Logger logger = Logger.getLogger(PolicyCancelledEventHandler.class.getName());

    public PolicyCancelledEventHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void on(PolicyCancelledEvent event) {
        var policyOptional = policyRepository.findById(event.getId());
        if (policyOptional.isPresent()) {
            var policy = policyOptional.get();
            policyOptional.get().setStatus(PolicyStatus.CANCELLED);
            policyRepository.save(policy);
        } else {
            logger.warning(MessageFormat.format("Policy {0} does not exist", event.getId()));
        }

    }
}
