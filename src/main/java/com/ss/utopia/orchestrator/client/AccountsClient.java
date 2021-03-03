package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.accounts.CreateUserAccountDto;
import com.ss.utopia.orchestrator.dto.accounts.NewPasswordDto;
import com.ss.utopia.orchestrator.dto.accounts.ResetPasswordDto;
import com.ss.utopia.orchestrator.security.SecurityConstants;
import java.util.UUID;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@ConfigurationProperties(prefix = "ss.utopia.accounts", ignoreUnknownFields = false)
public class AccountsClient {

  @Getter
  private final String endpoint = EndpointConstants.ACCOUNTS_ENDPOINT;
  @Setter
  private String apiHost;
  private RestTemplateBuilder builder;
  private RestTemplate restTemplate;

  @Autowired
  public void setBuilder(RestTemplateBuilder builder) {
    this.builder = builder;
  }

  @PostConstruct
  public void init() {
    restTemplate = builder.build();
  }

  public ResponseEntity<String> testMethod(String authHeader) {
    var url = apiHost + endpoint + "/test";
    log.info("GET " + url);
    var httpHeaders = new HttpHeaders();
    httpHeaders.add(SecurityConstants.JWT_HEADER_NAME, authHeader);
    var entity = new HttpEntity<>("", httpHeaders);
    return restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
  }

  public ResponseEntity<UUID> createNewAccount(CreateUserAccountDto dto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, dto, UUID.class);
  }


  public ResponseEntity<String> setPasswordResetToken(String emailObject) {
    var url = apiHost + endpoint + "/password-reset";
    log.debug("Post: " + url + ". Trying to initiate password reset");
    ResetPasswordDto emailToCheck = new ResetPasswordDto();
    emailToCheck.setEmail(emailObject);
    return restTemplate.postForEntity(url, emailToCheck, String.class);
  }

  public ResponseEntity<String> updatePassword(NewPasswordDto newPasswordDto) {
    var url = apiHost + endpoint + "/new-password";
    log.debug("Post: " + url + ". Changing password for this user who had this token");
    return restTemplate.postForEntity(url, newPasswordDto, String.class);
  }

  public ResponseEntity<String> checkToken(String token) {
    var url = apiHost + endpoint + "/new-password/" + token;
    log.debug("Get: " + url + ". Checking if a token is valid");
    return restTemplate.getForEntity(url, String.class);

  }

  public void confirmAccountRegistration(UUID confirmationTokenId) {
    var url = apiHost + endpoint + "/confirm/" + confirmationTokenId;
    log.info("PUT " + url);
    restTemplate.put(url, "");
  }
}