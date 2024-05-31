package com.merchan.cqrses.example.policy.cmd.api.controller;

import com.merchan.cqrses.example.core.infrastructure.CommandDispatcher;
import com.merchan.cqrses.example.policy.cmd.command.RestoreReadStoreCommand;
import com.merchan.cqrses.example.policy.common.dto.BaseResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/v1/policies/admin")
public class AdminPolicyController {

    private final Logger logger = Logger.getLogger(PolicyCmdController.class.getName());
    private final CommandDispatcher commandDispatcher;

    public AdminPolicyController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping("restore-read")
    public ResponseEntity<BaseResponse> restoreReadStore() {
        try {
            commandDispatcher.dispatch(new RestoreReadStoreCommand());
            return new ResponseEntity<>(new BaseResponse("Restore read store requested successfully"), HttpStatus.ACCEPTED);
        } catch (Exception e) {
            var errorMessage = "Error while restoring read store requested: " + e.getMessage();
            logger.log(Level.SEVERE, errorMessage, e);
            return new ResponseEntity<>(new BaseResponse(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
