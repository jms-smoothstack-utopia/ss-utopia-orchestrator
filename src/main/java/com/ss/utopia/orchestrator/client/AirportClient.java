package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.controller.EndpointConstants;
import com.ss.utopia.orchestrator.dto.flights.airport.CreateAirportDto;
import com.ss.utopia.orchestrator.dto.flights.airport.UpdateAirportDto;
import com.ss.utopia.orchestrator.models.flights.airport.Airport;
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
@ConfigurationProperties(prefix = "ss.utopia.airport", ignoreUnknownFields = false)
public class AirportClient {

  @Getter
  private final String endpoint = EndpointConstants.AIRPORTS_ENDPOINT;
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

  public ResponseEntity<Airport> getAirportById(Long id) {
    var url = apiHost + endpoint + id;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Airport.class);
  }

  public ResponseEntity<Airport[]> getAllAirports() {
    var url = apiHost + endpoint;
    log.info("GET " + url);
    return restTemplate.getForEntity(url, Airport[].class);
  }

  public ResponseEntity<Long> createAirport(CreateAirportDto airportDto) {
    var url = apiHost + endpoint;
    log.info("POST " + url);
    return restTemplate.postForEntity(url, airportDto, Long.class);
  }

  public void updateAirport(Long id, UpdateAirportDto airportDto) {
    var url = apiHost + endpoint + id;
    log.info("PUT " + url);
    restTemplate.put(url, airportDto);
  }

  public void deleteAirport(Long id) {
    var url = apiHost + endpoint + id;
    log.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
