package com.ss.utopia.orchestrator.client;

import com.ss.utopia.orchestrator.dto.AirportDto;
import com.ss.utopia.orchestrator.model.Airport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@ConfigurationProperties(prefix = "ss.utopia.airport", ignoreUnknownFields = false)
public class AirportClient {

  private static final Logger LOGGER = LoggerFactory.getLogger(AirportClient.class);
  private final RestTemplate restTemplate;
  private final String endpoint = "/airport/";
  private String apiHost;

  public AirportClient(RestTemplateBuilder restTemplateBuilder) {
    this.restTemplate = restTemplateBuilder.build();
  }

  public void setApiHost(String apiHost) {
    this.apiHost = apiHost;
  }

  public ResponseEntity<Airport> getAirportById(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Airport.class);
  }

  public ResponseEntity<Airport[]> getAllAirports() {
    var url = apiHost + endpoint;
    LOGGER.info("GET " + url);
    return restTemplate.getForEntity(url, Airport[].class);
  }

  public ResponseEntity<Long> createAirport(AirportDto airportDto) {
    var url = apiHost + endpoint;
    LOGGER.info("POST " + url);
    return restTemplate.postForEntity(url, airportDto, Long.class);
  }

  public void updateAirport(Long id, AirportDto airportDto) {
    var url = apiHost + endpoint + id;
    LOGGER.info("PUT " + url);
    restTemplate.put(url, airportDto);
  }

  public void deleteAirport(Long id) {
    var url = apiHost + endpoint + id;
    LOGGER.info("DELETE " + url);
    restTemplate.delete(url);
  }
}
