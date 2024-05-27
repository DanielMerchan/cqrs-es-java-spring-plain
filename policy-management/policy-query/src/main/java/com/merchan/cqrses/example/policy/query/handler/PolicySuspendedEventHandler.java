package com.merchan.cqrses.example.policy.query.handler;

import com.merchan.cqrses.example.core.handler.EventHandler;
import com.merchan.cqrses.example.policy.common.enums.PolicyStatus;
import com.merchan.cqrses.example.policy.common.event.PolicySuspendedEvent;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Logger;

@Service("PolicySuspendedEventHandler")
public class PolicySuspendedEventHandler implements EventHandler<PolicySuspendedEvent> {

    private final PolicyRepository policyRepository;

    private final Logger logger = Logger.getLogger(PolicySuspendedEventHandler.class.getName());

    public PolicySuspendedEventHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void on(PolicySuspendedEvent event) {
        var policyOptional = policyRepository.findById(event.getId());
        if (policyOptional.isPresent()) {
            var policy = policyOptional.get();
            policyOptional.get().setStatus(PolicyStatus.SUSPENDED);
            policyRepository.save(policy);
        } else {
            logger.warning(MessageFormat.format("Policy {0} does not exist", event.getId()));
        }

    }
}
