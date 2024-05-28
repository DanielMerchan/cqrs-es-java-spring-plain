package com.merchan.cqrses.example.policy.cmd.api.controller;

import com.merchan.cqrses.example.core.infrastructure.CommandDispatcher;
import com.merchan.cqrses.example.policy.cmd.api.dto.CreatePolicyResponse;
import com.merchan.cqrses.example.policy.cmd.command.CreatePolicyCommand;
import com.merchan.cqrses.example.policy.common.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.MessageFormat;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/policies")
public class PolicyController {
    private final Logger logger = Logger.getLogger(PolicyController.class.getName());
    private final CommandDispatcher commandDispatcher;

    public PolicyController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping
    public ResponseEntity<BaseResponse> createPolicy(CreatePolicyCommand createPolicyCommand) {
        var id = UUID.randomUUID().toString();
        createPolicyCommand.setId(id);
        try {
            commandDispatcher.dispatch(createPolicyCommand);
            return new ResponseEntity<>(new CreatePolicyResponse("Policy created successfully, go and celebrate it", id), HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Bad request - {0}", e.getMessage()),e);
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error while creating policy: {0}", id);
            logger.log(Level.SEVERE, errorMessage,e);
            return new ResponseEntity<>(new CreatePolicyResponse(errorMessage,id), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
