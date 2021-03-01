package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.flights.airplane.AirplaneDto;
import com.ss.utopia.orchestrator.models.flights.airplane.Airplane;
import javax.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Component
@ConfigurationProperties(prefix = "ss.utopia.airplane", ignoreUnknownFields = false)
public class AirplaneClient {

  @Getter
  private final String endpoint = EndpointConstants.AIRPLANES_ENDPOINT;
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

  public ResponseEntity<Airplane> getAirplaneById(Long id) {
    var url = apiHost + endpoint + id;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Airplane.class);
  }

  public ResponseEntity<Airplane[]> getAllAirplanes() {
    var url = apiHost + endpoint;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Airplane[].class);
  }

  public ResponseEntity<Long> createAirplane(AirplaneDto airplaneDto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, airplaneDto, Long.class);
  }


  public void updateAirplane(Long id, AirplaneDto airplaneDto) {
    var url = apiHost + endpoint + id;
    log.info("PUT " + url);
    restTemplate.put(url, airplaneDto);
  }

  public void deleteAirplane(Long id) {
    var url = apiHost + endpoint + id;
    log.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
