package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.auth.CreateUserAccountDto;
import java.util.UUID;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "ss.utopia.auth", ignoreUnknownFields = false)
public class AuthClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(AuthClient.class);
  private final String endpoint = "/accounts/";
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

  public ResponseEntity<UUID> createNewAccount(CreateUserAccountDto dto) {
    var url = apiHost + endpoint;
    LOGGER.info("POST " + url);
    return restTemplate.postForEntity(url, dto, UUID.class);
  }
}