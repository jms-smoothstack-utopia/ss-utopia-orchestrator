package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AuthClient;
import com.ss.utopia.orchestrator.dto.accounts.NewPasswordDto;
import com.ss.utopia.orchestrator.security.SecurityConstants;
import java.util.UUID;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(GatewayConstants.ACCOUNTS)
public class AccountsController {

  private final AuthClient accountsClient;

  @GetMapping("/test")
  public ResponseEntity<String> testMethod(
      @RequestHeader(SecurityConstants.JWT_HEADER_NAME) String authHeader) {
    log.info("GET test");
    return accountsClient.testMethod(authHeader);
  }

  @PostMapping(value = "/password-reset",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> setPasswordReset(@Valid @RequestBody String emailObject) {
    return accountsClient.setPasswordResetToken(emailObject);
  }

  @PostMapping(value = "/new-password",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> updatePassword(@Valid @RequestBody NewPasswordDto newPasswordDto) {
    return accountsClient.updatePassword(newPasswordDto);
  }

  @GetMapping(value = "/new-password/{token}",
      produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
  public ResponseEntity<String> checkToken(@PathVariable String token) {
    return accountsClient.checkToken(token);
  }

  @PutMapping("/confirm/{confirmationTokenId}")
  public ResponseEntity<?> confirmAccountRegistration(@PathVariable UUID confirmationTokenId) {
    log.info("Confirm tokenId=" + confirmationTokenId);
    accountsClient.confirmAccountRegistration(confirmationTokenId);
    return ResponseEntity.noContent().build();
  }
}
