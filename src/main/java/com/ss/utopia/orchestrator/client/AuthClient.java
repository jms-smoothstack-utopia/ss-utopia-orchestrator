package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.accounts.CreateUserAccountDto;
import com.ss.utopia.orchestrator.dto.accounts.NewPasswordDto;
import com.ss.utopia.orchestrator.dto.auth.AuthDto;
import java.util.UUID;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("utopia-auth-service")
public interface AuthClient {

  @PostMapping(EndpointConstants.AUTHENTICATE)
  ResponseEntity<String> authenticate(@RequestBody AuthDto authDto);

  @GetMapping(EndpointConstants.API_V_0_1_ACCOUNTS)
  ResponseEntity<String> testMethod(String authHeader);

  @PostMapping(EndpointConstants.API_V_0_1_ACCOUNTS)
  ResponseEntity<UUID> createNewAccount(@RequestBody CreateUserAccountDto dto);

  @PostMapping(EndpointConstants.API_V_0_1_ACCOUNTS + "/password-reset")
  ResponseEntity<String> setPasswordResetToken(@RequestBody String emailObject);

  @PostMapping(EndpointConstants.API_V_0_1_ACCOUNTS + "/new-password")
  ResponseEntity<String> updatePassword(@RequestBody NewPasswordDto newPasswordDto);

  @GetMapping(EndpointConstants.API_V_0_1_ACCOUNTS + "/new-password/{token}")
  ResponseEntity<String> checkToken(@PathVariable String token);

  @PutMapping(EndpointConstants.API_V_0_1_ACCOUNTS + "/confirm/{confirmationTokenId}")
  void confirmAccountRegistration(@PathVariable UUID confirmationTokenId);
}
