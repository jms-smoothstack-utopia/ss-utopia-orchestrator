package com.ss.utopia.orchestrator.controller;

import com.ss.utopia.orchestrator.client.AuthClient;
import com.ss.utopia.orchestrator.dto.auth.AuthDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin
@RestController
@RequestMapping(EndpointConstants.AUTHENTICATE_ENDPOINT)
public class AuthController {

  private final AuthClient authClient;

  @PostMapping
  public ResponseEntity<?> authenticate(@RequestBody AuthDto authDto) {
    log.info("POST authenticate.");
    return authClient.authenticate(authDto);
  }
}
