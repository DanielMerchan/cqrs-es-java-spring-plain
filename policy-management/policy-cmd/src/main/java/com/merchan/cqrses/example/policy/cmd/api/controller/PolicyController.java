package com.merchan.cqrses.example.policy.cmd.api.controller;

import com.merchan.cqrses.example.core.infrastructure.CommandDispatcher;
import com.merchan.cqrses.example.policy.cmd.api.dto.CreatePolicyResponse;
import com.merchan.cqrses.example.policy.cmd.command.CancelPolicyCommand;
import com.merchan.cqrses.example.policy.cmd.command.CreatePolicyCommand;
import com.merchan.cqrses.example.policy.cmd.command.RenewPolicyCommand;
import com.merchan.cqrses.example.policy.cmd.command.SuspendPolicyCommand;
import com.merchan.cqrses.example.policy.common.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<BaseResponse> createPolicy(@RequestBody CreatePolicyCommand createPolicyCommand) {
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

    @PatchMapping("/{id}/cancel")
    public ResponseEntity<BaseResponse> cancelPolicy(@PathVariable String id) {
        var cancelPolicyCommand = new CancelPolicyCommand(id);
        try {
            commandDispatcher.dispatch(cancelPolicyCommand);
            return new ResponseEntity<>(new BaseResponse("Policy has been cancelled"), HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Bad request - {0}", e.getMessage()),e);
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error while cancelling policy: {0}", id);
            logger.log(Level.SEVERE, errorMessage,e);
            return new ResponseEntity<>(new BaseResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/suspend")
    public ResponseEntity<BaseResponse> suspendPolicy(@PathVariable String id) {
        var suspendPolicyCommand = new SuspendPolicyCommand(id);
        try {
            commandDispatcher.dispatch(suspendPolicyCommand);
            return new ResponseEntity<>(new BaseResponse("Policy has been suspended"), HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Bad request - {0}", e.getMessage()),e);
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error while suspending policy: {0}", id);
            logger.log(Level.SEVERE, errorMessage,e);
            return new ResponseEntity<>(new BaseResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PatchMapping("/{id}/renew")
    public ResponseEntity<BaseResponse> renewPolicy(@PathVariable String id, @RequestBody RenewPolicyCommand renewPolicyCommand) {
        renewPolicyCommand.setId(id);
        try {
            commandDispatcher.dispatch(renewPolicyCommand);
            return new ResponseEntity<>(new BaseResponse("Policy has been renewed"), HttpStatus.OK);
        } catch (IllegalStateException e) {
            logger.log(Level.WARNING, MessageFormat.format("Bad request - {0}", e.getMessage()),e);
            return new ResponseEntity<>(new BaseResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            var errorMessage = MessageFormat.format("Error while renewing policy: {0}", id);
            logger.log(Level.SEVERE, errorMessage,e);
            return new ResponseEntity<>(new BaseResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
