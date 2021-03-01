package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.flights.flight.CreateFlightDto;
import com.ss.utopia.orchestrator.dto.flights.flight.UpdateFlightDto;
import com.ss.utopia.orchestrator.models.flights.flight.Flight;
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
@ConfigurationProperties(prefix = "ss.utopia.flight", ignoreUnknownFields = false)
public class FlightClient {

  @Getter
  private final String endpoint = EndpointConstants.FLIGHTS_ENDPOINT;
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

  public ResponseEntity<Flight> getFlightById(Long id) {
    var url = apiHost + endpoint + id;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Flight.class);
  }

  public ResponseEntity<Flight[]> getAllFlights() {
    var url = apiHost + endpoint;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Flight[].class);
  }

  public ResponseEntity<Long> createFlight(CreateFlightDto flightDto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, flightDto, Long.class);
  }

  public void updateFlight(Long id, UpdateFlightDto flightDto) {
    var url = apiHost + endpoint + id;
    log.info("PUT " + url);
    restTemplate.put(url, flightDto);
  }

  public void deleteFlight(Long id) {
    var url = apiHost + endpoint + id;
    log.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
