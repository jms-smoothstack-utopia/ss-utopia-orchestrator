package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AccountsClient;
import com.ss.utopia.orchestrator.security.SecurityConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(EndpointConstants.ACCOUNTS_ENDPOINT)
public class AccountsController {

  private final AccountsClient accountsClient;

  @GetMapping("/test")
  public ResponseEntity<String> testMethod(
      @RequestHeader(SecurityConstants.JWT_HEADER_NAME) String authHeader) {
    return accountsClient.testMethod(authHeader);
  }

}
