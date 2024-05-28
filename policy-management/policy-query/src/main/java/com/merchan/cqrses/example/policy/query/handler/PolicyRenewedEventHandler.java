package com.merchan.cqrses.example.policy.query.handler;

import com.merchan.cqrses.example.core.handler.EventHandler;
import com.merchan.cqrses.example.policy.common.enums.PolicyPeriod;
import com.merchan.cqrses.example.policy.common.event.PolicyRenewedEvent;
import com.merchan.cqrses.example.policy.query.infrastructure.PolicyRepository;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.logging.Logger;

@Service("PolicyRenewedEventHandler")
public class PolicyRenewedEventHandler implements EventHandler<PolicyRenewedEvent> {

    private final PolicyRepository policyRepository;

    private final Logger logger = Logger.getLogger(PolicyRenewedEventHandler.class.getName());

    public PolicyRenewedEventHandler(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    @Override
    public void on(PolicyRenewedEvent event) {
        var policyOptional = policyRepository.findById(event.getId());
        if (policyOptional.isPresent()) {
            var policy = policyOptional.get();
            PolicyPeriod renewPeriod = event.getPolicyPeriod();
            switch (renewPeriod) {
                case SIX_MONTHS -> policy.setEndDate(event.getEndDate().plusMonths(6));
                case YEAR -> policy.setEndDate(event.getEndDate().plusYears(1));
                case TWO_YEAR -> policy.setEndDate(event.getEndDate().plusYears(2));
            }
            policy.setEndDate(event.getEndDate());
            policyRepository.save(policy);
        } else {
            logger.warning(MessageFormat.format("Policy {0} does not exist", event.getId()));
        }

    }
}
