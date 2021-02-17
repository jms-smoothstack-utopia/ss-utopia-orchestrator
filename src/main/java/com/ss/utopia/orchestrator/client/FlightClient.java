package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.flights.flight.CreateFlightDto;
import com.ss.utopia.orchestrator.dto.flights.flight.UpdateFlightDto;
import com.ss.utopia.orchestrator.models.flights.flight.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "ss.utopia.flight", ignoreUnknownFields = false)
public class FlightClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(FlightClient.class);
  private final RestTemplate restTemplate;
  private final String endpoint = "/flight/";
  private String apiHost;

  public FlightClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }

  public ResponseEntity<Flight> getFlightById(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Flight.class);
  }

  public ResponseEntity<Flight[]> getAllFlights() {
    var url = apiHost + endpoint;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Flight[].class);
  }

  public ResponseEntity<Long> createFlight(CreateFlightDto flightDto) {
    var url = apiHost + endpoint;
    LOGGER.info("POST " + url);
    return restTemplate.postForEntity(url, flightDto, Long.class);
  }

  public void updateFlight(Long id, UpdateFlightDto flightDto) {
    var url = apiHost + endpoint + id;
    LOGGER.info("PUT " + url);
    restTemplate.put(url, flightDto);
  }

  public void deleteFlight(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
