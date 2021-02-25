package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AccountsClient;
import com.ss.utopia.orchestrator.dto.accounts.NewPasswordDto;
import com.ss.utopia.orchestrator.security.SecurityConstants;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

  @PostMapping(value= "/password-reset", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> passwordResetMethod(@Valid @RequestBody String emailObject){
    return accountsClient.setPasswordResetToken(emailObject);
  }

  @PostMapping(value= "/new-password", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> updatePassword(@Valid @RequestBody NewPasswordDto newPasswordDto){
    return accountsClient.updatePassword(newPasswordDto);
  }
}
