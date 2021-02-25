package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.auth.AuthDto;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Slf4j
@ConfigurationProperties(prefix = "ss.utopia.auth", ignoreUnknownFields = false)
public class AuthClient {

  private final String endpoint = EndpointConstants.AUTHENTICATE_ENDPOINT;
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

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }

  public String getEndpoint() {
    return endpoint;
  }

  public ResponseEntity<String> authenticate(AuthDto authDto) {
    var url = apiHost + endpoint;
    log.debug("POST " + url);
    return restTemplate.postForEntity(url, authDto, String.class);
  }

  public ResponseEntity<String> setPasswordResetToken(String emailObject) {
    var url = apiHost + "/api/v0.1/accounts" + "/password-reset";
    log.debug("Post: " + url + ". Trying to initiate password reset");
    return restTemplate.postForEntity(url, emailObject, String.class );
  }
}
